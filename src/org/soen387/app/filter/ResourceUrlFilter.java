package org.soen387.app.filter;

import javax.servlet.annotation.WebFilter;

import org.dsrg.soenea.application.filter.PermalinkFilter;

@WebFilter("/Game/*")
public class ResourceUrlFilter extends PermalinkFilter {

}
