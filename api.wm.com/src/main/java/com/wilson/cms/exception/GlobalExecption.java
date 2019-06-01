package com.wilson.cms.exception;

import com.wilson.cms.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExecption
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/1 21:56
 * @Version 1.0
 **/
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExecption {
    private static Logger logger = LoggerFactory.getLogger(GlobalExecption.class);
//
//    /**
//     * 非法访问
//     *
//     * @param ex 非法访问的异常对象
//     * @return 响应体
//     */
//    @ExceptionHandler(InvalidPermissionException.class)
//    public ResponseEntity<ApiResponse<Void>> handleInvalidPermissionException(
//            InvalidPermissionException ex) {
//        logger.error("操作失败：{}", ex.getMessage());
//
//        // API返回结果
//        ApiResponse<Void> apiResponse = new ApiResponse<>(
//                ApiResponseCode.CODE_INVALID_PERMISSION.getCode(), ex.getMessage());
//
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * 重复操作
//     *
//     * @param ex 重复操作的异常对象
//     * @return 响应体
//     */
//    @ExceptionHandler(DuplicatedOperationException.class)
//    public ResponseEntity<ApiResponse<Void>> handleDuplicatedOperationException(
//            DuplicatedOperationException ex) {
//        logger.error("操作失败：{}", ex.getMessage());
//
//        // API返回结果
//        ApiResponse<Void> apiResponse = new ApiResponse<>(
//                ApiResponseCode.CODE_DUPLICATED_OPERATION.getCode(), ex.getMessage());
//
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }


    /**
     * 其他异常都按照"未知异常"处理
     *
     * @param e 异常对象
     * @return 响应体
     */
    @ExceptionHandler(Exception.class)
    public Result otherException(Exception ex) {
        logger.error("操作失败：{}", ex);
        return Result.Execption("服务繁忙，请稍候，或联系管理员。");
//        ApiResponseCode apiResponseCode = ApiResponseCode.CODE_UNKNOWN;
//
//        // API 返回结果
//        ApiResponse<Void> apiResponse = new ApiResponse<>(apiResponseCode);
//
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<ApiResponse<Void>> otherException(Exception e) {
//        logger.error("操作失败：{}", e);
//
//        ApiResponseCode apiResponseCode = ApiResponseCode.CODE_UNKNOWN;
//
//        // API 返回结果
//        ApiResponse<Void> apiResponse = new ApiResponse<>(apiResponseCode);
//
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }
}
