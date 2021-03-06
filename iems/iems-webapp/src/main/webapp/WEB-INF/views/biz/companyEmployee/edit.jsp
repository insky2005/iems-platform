<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | CompanyEmployee - Edit</title>

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
                                  <input type="hidden" id="companyid" name="companyEmployee.companyid" />
                                
                                  <div class="box-header">
                                      <h3 class="box-title">基本信息</h3>
                                  </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-12">
                                        <div class="row">
                                          <div class="col-xs-4 form-group h_90px">
                                            <label for="name">姓名</label>
                                            <input type="text" class="form-control" id="name" name="companyEmployee.name" placeholder="输入姓名">
                                          </div>
                                        </div>
                                        </div>
                                        
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="staffno">工号</label>
                                            <input type="text" class="form-control" id="staffno" name="companyEmployee.staffno" placeholder="输入工号">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="department">部门</label>
                                            <input type="text" class="form-control" id="department" name="companyEmployee.department" placeholder="输入部门">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="job">职位</label>
                                            <input type="text" class="form-control" id="job" name="companyEmployee.job" placeholder="输入职位">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="telephone">联系电话</label>
                                            <input type="text" class="form-control" id="telephone" name="companyEmployee.telephone" placeholder="输入联系电话">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="qq">QQ</label>
                                            <input type="text" class="form-control" id="qq" name="companyEmployee.qq" placeholder="输入QQ号">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="wechat">微信号</label>
                                            <input type="text" class="form-control" id="wechat" name="companyEmployee.wechat" placeholder="输入微信号">
                                        </div>
                                    </div><!-- /.box-body -->

                                  <div class="box-header">
                                      <h3 class="box-title">帐户信息</h3>
                                  </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="username">帐户名</label>
                                            <input type="text" class="form-control" id="username" name="sysUser.username" placeholder="输入帐户名">
                                        </div>

                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="email">邮箱</label>
                                            <input type="email" class="form-control" id="email" name="sysUser.email" placeholder="输入邮件地址">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="mobile">手机</label>
                                            <input type="text" class="form-control" id="mobile" name="sysUser.mobile" placeholder="输入手机号码">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="password">密码</label>
                                            <input type="password" class="form-control" id="password" name="sysUser.password" placeholder="输入密码">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="passcheck">确认密码</label>
                                            <input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="确认密码">
                                        </div>
                                    </div><!-- /.box-body -->

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


        <!-- AdminLTE App -->
        <script src="${ctx }/assets/js/AdminLTE/app.js" type="text/javascript"></script>


        <script src="${ctx }/js/iEvent/biz/companyemployee/edit.js" type="text/javascript"></script>

  </body>
</html>