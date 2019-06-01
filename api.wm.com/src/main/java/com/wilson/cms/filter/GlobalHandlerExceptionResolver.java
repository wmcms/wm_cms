package com.wilson.cms.filter;

import com.wilson.cms.utils.JsonUtils;
import com.wilson.cms.vo.Result;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
    /**
     * 在这里处理所有得异常信息
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   Object o, Exception ex) {

            logger.warning( ex.getMessage());
        printWrite(ex.getMessage(), httpServletResponse);
                    return new ModelAndView();
//          if (ex instanceof AthenaException) {
//                   //AthenaException为一个自定义异常
//                   ex.printStackTrace();
//                   printWrite(ex.toString(), resp);
//                   return new ModelAndView();
//                }
//           //RspMsg为一个自定义处理异常信息的类
//           //ResponseCode为一个自定义错误码的接口
//           RspMsg unknownException = null;
//           if (ex instanceof NullPointerException) {
//                   unknownException = new RspMsg(ResponseCode.CODE_UNKNOWN, "业务判空异常", null);
//               } else {
//                   unknownException = new RspMsg(ResponseCode.CODE_UNKNOWN, ex.getMessage(), null);        }
//                       printWrite(unknownException.toString(), resp);
//                      return new ModelAndView();
        }

        /**
        * 将错误信息添加到response中
        *
        * @param msg
        * @param response
        * @throws IOException
        */
      public static void printWrite(String msg, HttpServletResponse response) {
         try {
                 PrintWriter pw = response.getWriter();
             pw.print(JsonUtils.obj2String(Result.Execption(msg)));
                 pw.flush();
                 pw.close();
              } catch (Exception e) {
                 e.printStackTrace();
              }
             }
}
