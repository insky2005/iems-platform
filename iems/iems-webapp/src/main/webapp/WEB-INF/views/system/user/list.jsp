<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | User - List</title>

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
                        系统管理
                        <small>帐户</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li>系统管理</li>
                        <li class="active">帐户</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">帐户列表</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <a href="#" class="btn btn-sm btn-info" data-src="add.do" data-toggle="modal" data-target="#modal" data-title="新增帐户">新增</a>
                                    <table id="table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>帐户</th>
                                                <th>帐户类型</th>
                                                <th>邮箱</th>
                                                <th>手机</th>
                                                <th>是否启用</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        
                                        </tbody>
                                    </table>
                                </div><!-- /.box-body -->
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


        <!-- DATA TABES SCRIPT -->
        <script src="${ctx }/assets/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="${ctx }/assets/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        

        <script src="${ctx }/js/iEvent/system/user/list.js" type="text/javascript"></script>

  </body>
</html>