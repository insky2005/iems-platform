<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | CompanyInfo - Index</title>

    <meta name="description" content="index page" />
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <jsp:include page="/include/head.jsp"></jsp:include>
  </head>
  <body class="skin-blue fixed">

        <!-- header logo: style can be found in header.less -->
        <jsp:include page="/include/header.jsp"></jsp:include>


        <div class="wrapper row-offcanvas row-offcanvas-left">

            <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="/include/left.jsp"></jsp:include>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        企业管理
                        <small>企业基本信息</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li>企业管理</li>
                        <li class="active">企业基本信息</li>
                    </ol>
                </section>

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


<!-- Modal -->
<div class="modal" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <iframe frameborder="0" style="width: 100%; height: 500px;"></iframe>
      </div>
    </div>
  </div>
</div>

        <jsp:include page="/include/foot.jsp"></jsp:include>

        
        <script src="${ctx }/js/iEvent/biz/companyinfo/index.js" type="text/javascript"></script>

  </body>
</html>