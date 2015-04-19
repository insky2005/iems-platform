<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | EventStaff - Add</title>

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
                                  <input type="hidden" id="companyid" name="eventPersonnel.companyid" />
                                  <input type="hidden" id="eventid" name="eventPersonnel.eventid" />
                                  
                                  <div class="box-header">
                                      <h3 class="box-title">基本信息</h3>
                                  </div><!-- /.box-header -->
                                  <div class="row box-body">
                                  <div class="col-xs-12">
                                    <div class="row">
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="type">人员类型</label>
                                            <div>
                                            <label>
		                                            <input type="radio" name="eventPersonnel.type" class="minimal" value="3" /> VIP
		                                        </label>
		                                        <label>
		                                            <input type="radio" name="eventPersonnel.type" class="minimal" value="4" checked /> 参会人员
		                                        </label>
		                                        </div>
                                        </div>

                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="name">姓名</label>
                                            <input type="text" class="form-control" id="name" name="eventPersonnel.name" placeholder="输入姓名">
                                        </div>
                                        <%--
                                        <div class="col-xs-4 form-group h_90px media-bottom">
                                            <label for="">&nbsp;</label><br/>
                                            <a id="chooseCompanyEmployee" href="#" class="btn btn-sm btn-info" data-toggle="modal" data-target="#modal" data-title="选择企业员工">选择企业员工</a>
                                            <input type="hidden" id="employeeid" name="eventPersonnel.employeeid" />
                                        </div>
                                         --%>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="telephone">联系电话</label>
                                            <input type="text" class="form-control" id="telephone" name="eventPersonnel.telephone" placeholder="输入联系电话">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="qq">QQ</label>
                                            <input type="text" class="form-control" id="qq" name="eventPersonnel.qq" placeholder="输入QQ号">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="wechat">微信号</label>
                                            <input type="text" class="form-control" id="wechat" name="eventPersonnel.wechat" placeholder="输入微信号">
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-xs-12 form-group h_150px">
                                            <label for="memo">备注</label>
                                            <textarea class="form-control" id="memo" name="eventPersonnel.memo" rows="4" placeholder="输入备注"></textarea>
                                        </div>
                                    </div>
                                  </div>
                                  </div><!-- /.box-body -->

                                  <div class="box-header">
                                      <h3 class="box-title">帐户信息</h3>
                                  </div><!-- /.box-header -->
                                  <div class="row box-body">
                                  <div class="col-xs-12">
                                    <div class="row">
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
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="password">密码</label>
                                            <input type="password" class="form-control" id="password" name="sysUser.password" placeholder="输入密码">
                                        </div>
                                        <div class="col-xs-4 form-group h_90px">
                                            <label for="passcheck">确认密码</label>
                                            <input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="确认密码">
                                        </div>
                                    </div>
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

<!-- Modal -->
<div class="modal" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="modal-data" style="width: 100%; height: 280px; overflow: auto;"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary ok">确定</button>
        <button type="button" class="btn btn-danger cancel">关闭</button>
      </div>
    </div>
  </div>
</div>

        <jsp:include page="/include/foot.jsp"></jsp:include>


        <!-- AdminLTE App -->
        <script src="${ctx }/assets/js/AdminLTE/app.js" type="text/javascript"></script>


        <script src="${ctx }/js/iEvent/biz/eventpersonnel/add.js" type="text/javascript"></script>

  </body>
</html>