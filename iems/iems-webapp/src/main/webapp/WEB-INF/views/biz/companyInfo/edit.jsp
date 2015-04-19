<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | CompanyInfo - Edit</title>

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
	                                <div class="box-header">
	                                    <h3 class="box-title">企业基本信息</h3>
	                                </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="name">企业名称</label>
                                            <input type="text" class="form-control" id="name" name="companyInfo.name" placeholder="输入企业名称">
                                        </div>

                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="type">企业类型</label>
                                            <input type="text" class="form-control" id="type" name="companyInfo.type" placeholder="输入企业类型">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="address">地址</label>
                                            <input type="text" class="form-control" id="address" name="companyInfo.address" placeholder="输入地址">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="telephone">电话</label>
                                            <input type="text" class="form-control" id="telephone" name="companyInfo.telephone" placeholder="输入电话">
                                        </div>
                                        <div class="col-xs-6 form-group h_90px">
                                            <label for="fax">传真</label>
                                            <input type="text" class="form-control" id="fax" name="companyInfo.fax" placeholder="输入传真">
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


        <script src="${ctx }/js/iEvent/biz/companyinfo/edit.js" type="text/javascript"></script>

  </body>
</html>