package com.wilson.cms.exception;

/**
 * @ClassName NotSupportExecption
 * @Description TODO
 * @Author wilson
 * @Date 2019/5/29 22:01
 * @Version 1.0
 **/
public class NotSupportExecption extends RuntimeException {
    private static final long serialVersionUID = 5162710183389028792L;

    /**
     * Constructs a {@code NullPointerException} with no detail message.
     */
    public NotSupportExecption() {
        super();
    }

    /**
     * Constructs a {@code NullPointerException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public NotSupportExecption(String msg) {
        super(msg);
    }
}
