package jxau.soft.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Controller
public class FileUpAndDown {
	private String fileName="";
    @RequestMapping(value="jsp/afileUpLoad")
    public String testUpload(HttpServletRequest request,@RequestParam(value="desc",required=false) String desc,@RequestParam("photo") CommonsMultipartFile file) throws Exception{
        String path=System.getProperty("catalina.home");
        if(file.getSize()==0) {
        	System.out.println("文件为空");
        	  return "redirect:File.jsp";
        }
        path=path+"\\"+"homework";
         File  file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();   //如果该目录不存在，就创建此抽象路径名指定的目录。 
        }
         fileName =file.getOriginalFilename();//使用UUID加前缀命名文件，防止名字重复被覆盖
         String prefix = UUID.randomUUID().toString();
	        prefix = prefix.replace("-","");
	        fileName = prefix+"_"+file.getOriginalFilename();
        InputStream in= file.getInputStream();;//声明输入输出流
        OutputStream out=new FileOutputStream(new File(path+"\\"+fileName));//指定输出流的位置;
        byte []buffer =new byte[1024];
        int len=-1;
        while((len=in.read(buffer))!=-1){
            out.write(buffer, 0, len);
            out.flush();                //类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
        }                               //这段代码也可以用IOUtils.copy(in, out)工具类的copy方法完成
        out.close();
        in.close();
    System.out.println("上传成功");
    System.out.println(fileName);
    return "redirect:success.jsp";
    }
    // D:\Tomcat\apache-tomcat-9.0.8\wtpwebapps\HomeWorkSystem\WEB-INF\童话镇.mp3
    @RequestMapping("jsp/afileDownLoad")
    public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request) throws Exception{
    	  String path=System.getProperty("catalina.home");
          path=path+"\\"+"homework";
        InputStream in=new FileInputStream(new File(path+"\\"+fileName));//将该文件加入到输入流之中
         byte[] body=null;
         body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
         in.read(body);//读入到输入流里面
        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }
}