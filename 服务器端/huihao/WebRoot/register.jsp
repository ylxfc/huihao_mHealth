<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<script language="javascript">
  function check()
  {
    seridlen=window.document.form2.serId.value.length;
    serpassword=window.document.form2.serPassword.value.length;
    sername=window.document.form2.serName.value.length;
    sersexlen=window.document.form2.serSex.value.length;
    seragelen=window.document.form2.serAge.value.length;
    serphonelen=window.document.form2.serPhone.value.length;
    medidlen=window.document.form2.medId.value.length;
    havsignedagencylen=window.document.form2.havSignedAgency.value.length;
    havvisitedlen=window.document.form2.havVisited.value.length;
    serid=window.document.form2.serId.value;
    sersex=window.document.form2.serSex.value;
    serage=window.document.form2.serAge.value;
    serphone=window.document.form2.serPhone.value;
    medid=window.document.form2.medId.value;
    havsignedagency=window.document.form2.havSignedAgency.value;
    havvisited=window.document.form2.havVisited.value;
    if(seridlen==0) alert("请输入业务员编号");
    else if(serpassword==0)  alert("请输入业务员密码");
    else if(sername==0) alert("请输入业务员姓名");
    else if(sersexlen==0) alert("请输入业务员性别");
    else if(seragelen==0) alert("请输入业务员年龄");
    else if(serphonelen==0) alert("请输入业务员联系方式");
    else if(medidlen==0) alert("请输入药品种类");
    else if(havsignedagencylen==0) alert("请输入签约经销商数量");
    else if(havvisitedlen==0) alert("请输入已拜访经销商数量");
    else if(isNaN(serid))  alert("业务员编号必须为数字，请重新输入");
    else if(sersex!="男"&&sersex!="女")  alert("性别输入无效，请输入男或女");
    else if(isNaN(serage))  alert("年龄必须为数字，请重新输入");
    else if(isNaN(serphone)) alert("联系方式必须为数字，请重新输入");
    else if(!isNaN(medid))  alert("药品种类不能为数字，请重新输入");
    else if(isNaN(havsignedagency))  alert("签约经销商数量必须为数字，请重新输入");
    else if(isNaN(havvisited))  alert("已拜访经销商数量必须为数字，请重新输入");
    else form2.submit();
  }
</script>
</head>
<body background="images/bg.png">
<table cellpadding=0 cellspacing=0  width="100%" align="center" >
	<tr>
	  <td >
	  			
		    <table width="90%" border="0" cellspacing="0" cellpadding="0"  align="center">
              <tr>
                <td>
                <form name="form2" method="post" action="reg.do" >
                <p>&nbsp;</p>
                  <h3 align="center">请认真填写注册信息</h3>
                  <table width="300" border="0" align="center">
                    <tr>
                      <td width="100">业务员编号：</td>
                      <td width="184"><label>
                        <input name="serId" type="text" id="serId">
                      </label></td>
                    </tr>
                    <tr>
                      <td>密码：</td>
                      <td><label>
                        <input name="serPassword" type="text" id="serPassword">
                      </label></td>
                    </tr>
					<tr>
                      <td>姓名：</td>
                      <td><label>
                        <input name="serName" type="text" id="serName">
                      </label></td>
                    </tr>                    
					<tr>
                      <td>性别：</td>
                      <td><label>
                        <input name="serSex" type="text" id="serSex">
                      </label></td>
                    </tr>
                    <tr>
                      <td>年龄：</td>
                      <td><label>
                        <input name="serAge" type="text" id="serAge">
                      </label></td>
                    </tr>
                    <tr>
                      <td>电话</td>
                      <td><label>
                        <input name="serPhone" type="text" id="serPhone">
                      </label></td>
                    </tr>
                    <tr>
                      <td>销售药品批准文号：</td>
                      <td><label>
                        <input name="medId" type="text" id="medId">
                      </label></td>
                    </tr>
                    <tr>
                      <td>已签约经销商数量：</td>
                      <td><label>
                        <input name="havSignedAgency" type="text" id="havSignedAgency">
                      </label></td>
                    </tr>
                     <tr>
                      <td>已拜访经销商数量：</td>
                      <td><label>
                        <input name="havVisited" type="text" id="havVisited">
                      </label></td>
                    </tr>
				    <tr>
                      <td colspan="2" align="center"><label>
                        <input type="button" name="sub" value="提交" onclick="check()">
                        <input name="reset" type="reset" id="reset" value="重置">
                      </label></td>
                    </tr>
                  </table>
                </form>
                </td>
              </tr>
            </table></td>
	</tr>
</table>
</body>
</html>