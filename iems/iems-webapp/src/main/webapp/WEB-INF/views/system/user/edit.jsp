<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | User - Edit</title>

    <meta name="description" content="index page" />
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <jsp:include page="/include/head.jsp"></jsp:include>
  </head>
  <body class="skin-blue fixed">

        <div class="">

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side strech">
                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <!-- form start -->
                                <form role="form" id="form_data" action="save.do" method="post">
                                  <input type="hidden" id="usertype" name="sysUser.usertype" />
	                                <div class="box-header">
	                                    <h3 class="box-title">帐户信息</h3>
	                                </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-12 form-group h_90px">
                                          <div class="w_50per padding-right_15px">
                                            <label for="username">帐户名</label>
                                            <input type="text" class="form-control" id="username" name="sysUser.username" placeholder="输入帐户名">
                                          </div>
                                        </div>

                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="email">邮箱</label>
                                            <input type="email" class="form-control" id="email" name="sysUser.email" placeholder="输入邮件地址">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="mobile">手机</label>
                                            <input type="text" class="form-control" id="mobile" name="sysUser.mobile" placeholder="输入手机号码">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="password">密码</label>
                                            <input type="password" class="form-control" id="password" name="sysUser.password" placeholder="输入密码">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="passcheck">确认密码</label>
                                            <input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="确认密码">
                                        </div>
                                    </div><!-- /.box-body -->

<%-- 
                                  <div class="box-header">
                                      <h3 class="box-title">基本信息</h3>
                                  </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="name">姓名</label>
                                            <input type="text" class="form-control" id="name" name="name" placeholder="输入您的姓名">
                                        </div>
                                    </div><!-- /.box-body -->
 --%>
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">保存</button>
                                    </div>
                                </form>
                                
                            </div><!-- /.box -->

                        </div>
                    </div>
                    
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

<!-- Confirm -->
<div class="modal confirm_modal" id="confirm">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body inner"></div>
      <div class="modal-footer">
        <a class="btn btn-default cancel" href="#" data-dismiss="modal">cancel</a>
        <a href="#" class="btn btn-danger" data-action="1">yes</a>
      </div>
    </div>
  </div>
</div>

        <jsp:include page="/include/foot.jsp"></jsp:include>


        <script src="${ctx }/js/iEvent/system/user/edit.js" type="text/javascript"></script>

  </body>
</html>