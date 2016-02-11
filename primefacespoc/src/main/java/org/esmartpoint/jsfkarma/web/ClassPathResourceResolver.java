package org.esmartpoint.jsfkarma.web;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

public class ClassPathResourceResolver extends ResourceResolver  {

    public URL resolveUrl(String path) {
	    return getClass().getResource(path);
    }
}

