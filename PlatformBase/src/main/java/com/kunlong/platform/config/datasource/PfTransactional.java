package com.kunlong.platform.config.datasource;

import org.springframework.core.annotation.AliasFor;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional
public @interface PfTransactional {
//    @AliasFor("transactionManager")
//@AliasFor("value")
//    @AliasFor(
//            annotation = Transactional.class
//    )
//    String value() default "pfTransactionManager";

    //@AliasFor("value")
    @AliasFor(
        annotation = Transactional.class
    )
    String transactionManager() default "pfTransactionManager";

    Propagation propagation() default Propagation.REQUIRED;

    Isolation isolation() default Isolation.DEFAULT;

    int timeout() default -1;

    boolean readOnly() default false;

    Class<? extends Throwable>[] rollbackFor() default {};

    String[] rollbackForClassName() default {};

    Class<? extends Throwable>[] noRollbackFor() default {};

    String[] noRollbackForClassName() default {};
}
