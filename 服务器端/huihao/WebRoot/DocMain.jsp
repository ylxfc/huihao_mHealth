<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script language="javascript">
	function openHref(main,child){					
		parent.topMenuFrame.location.href=main;
		parent.mainFrame.location.href=child;
	}
</script>
<html xmlns="http://www.w3.org/1999/xhtml">
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
                <title>慧好制药业务管理系统</title>
                <link href="css/main.css" rel="stylesheet">
        </head>
        <body>
                <div id="navigation">
                        <ul>
                                <!-- CSS Tabs -->
                              
<li> <a href="Content.jsp" target="mainFrame"><span>首&nbsp;页</span></a></li>
<li> <a href="exit.do" target="_top"><span>注&nbsp;销</span></a></li>
                        </ul>
                </div>
        </body>
</html>