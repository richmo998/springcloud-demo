package com.banling.sc.service.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banling.sc.module.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/hello")  
public class HelloController {
	
	private final Logger logger=Logger.getLogger(this.getClass());
	
	@ApiOperation(value="获取消息", notes="根据传入参数获取响应消息")
    @ApiImplicitParam(name = "msg", value = "入参", required = true, dataType = "String",paramType = "query")
	@RequestMapping(value="/getMsg",method=RequestMethod.GET)
	public String getMsg(String msg){
		logger.info("getMsg");
		return "What msg do get from client 8080 :"+msg;
	}
	
	@ApiOperation(value="获取消息", notes="根据传入参数获取响应消息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "msg1", value = "入参1", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "msg2", value = "入参2", required = true, dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/getMoreMsgs",method=RequestMethod.GET)
	public String getMoreMsgs(String msg1,String msg2){
		logger.info("getMoreMsgs");
		return msg1+"   ...   "+msg2;
	}
	
	//显式声明返回为JSON
	@ApiOperation(value="获取用户信息列表", notes="根据传入参数获取响应消息")
	@ApiImplicitParam(name = "count", value = "返回的记录数量", required = true, dataType = "Integer",paramType = "query")
	@GetMapping(value="getUsers",produces = "application/json;charset=UTF-8")
	public List<User> getUsers(Integer count){
		logger.info("getUsers");
		List<User> list=null;
		if(count!=null&&count.intValue()>0){
			list=new ArrayList<User>();
			for(int i=0;i<count.intValue();i++){
				list.add(new User(i,"userName_"+i,new Date()));
			}
		}
		return list;
	}
	
	
	@ApiOperation(value="获取用户信息列表", notes="根据传入参数获取响应消息")
	@ApiImplicitParam(name = "count", value = "返回的记录数量", required = true, dataType = "Integer",paramType = "path")
	@GetMapping("userList/{count}")
	public List<User> userList(@PathVariable Integer count){
		logger.info("userList");
		List<User> list=null;
		if(count!=null&&count.intValue()>0){
			list=new ArrayList<User>();
			for(int i=0;i<count.intValue();i++){
				list.add(new User(i,"userName_"+i,new Date()));
			}
		}
		return list;
	}
	
	@ApiOperation(value="文件ID", notes="原始方式实现：根据文件ID下载文件")
	@ApiImplicitParam(name = "fileid", value = "文件ID", required = true, dataType = "String",paramType = "path")
	@GetMapping("download/{fileid}")
	public void fileDownLoad(@PathVariable String fileid,HttpServletResponse response){
		logger.info("download");
        File file=new File("D:\\wubi.jpg");  
        String filename = file.getName();
        InputStream inputStream=null;
        try {
        	//通过Buffer操作IO
        	inputStream = new BufferedInputStream(new FileInputStream(file));
        	//一次性读写。文件大时体验不好，不建议
        	//byte[] buffer = new byte[inputStream.available()];//获取文件总长度
        	//inputStream.read(buffer); //一次全部读取
        	
        	//分段读、写
        	//建议这种方式
        	byte[] buffer =new byte[1024];
        	OutputStream os = new BufferedOutputStream(response.getOutputStream());
        	int i =0;
        	//一次读取1024个字节到buffer中
        	while ((i = inputStream.read(buffer)) != -1) {
        		os.write(buffer, 0, i);// 输出文件
        	}
        	
        	inputStream.close();
        	//去空格；并保证文件名不出现乱码
        	response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
        	response.addHeader("Content-Length", "" + file.length());
        	response.addHeader("filename", new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
        	response.setContentType("application/octet-stream");
        	os.flush();
        	os.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	/**上传文件的工具
	 * @param file
	 */
	private void singleFileUpload(MultipartFile file){
		try {
			String orignalFileName=file.getOriginalFilename();
			logger.info("上传的文件："+orignalFileName+",保存到E盘。");
            //获取输出流，保存到本地
            OutputStream os=new FileOutputStream("E:/"+new Date().getTime()+orignalFileName.substring(orignalFileName.lastIndexOf(".")));
            BufferedOutputStream bufOut=new BufferedOutputStream(os);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            BufferedInputStream bufIn=new BufferedInputStream(is);
            int temp;
            //一段一段地读写
            byte[] buffer=new byte[1024];
            while((temp=bufIn.read(buffer))!=-1){
            	bufOut.write(buffer, 0, temp);
            }
            //也可以直接把文件保存到本地，而不用自己实现对流的操作
            //file.transferTo(new File(file.getOriginalFilename()));
            bufOut.flush();
            bufOut.close();
            bufIn.close();
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	@ApiOperation(value="null",notes="可以上传一个或者多个文件")
	@PostMapping("filesupload")
	public void filesUpload(@RequestParam("file") MultipartFile file[]){
		logger.info("上传文件");
		for(int i=0;i<file.length;i++){
			singleFileUpload(file[i]);
		}
	}
	
}
