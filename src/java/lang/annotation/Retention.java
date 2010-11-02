/*
 * @(#)Retention.java	1.6 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang.annotation;

/**
 * Indicates how long annotations with the annotated type are to
 * be retained.  If no Retention annotation is present on
 * an annotation type declaration, the retention policy defaults to
 * <tt>RetentionPolicy.CLASS</tt>.
 *
 * <p>A Target meta-annotation has effect only if the meta-annotated
 * type is use directly for annotation.  It has no effect if the meta-annotated
 * type is used as a member type in another annotation type.
 *
 * @author  Joshua Bloch
 * @since 1.5
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Retention {
    int value();
}
