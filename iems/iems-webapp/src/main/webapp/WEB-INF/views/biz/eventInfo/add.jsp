<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>${systemName } | EventList - Add</title>

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
                                  <input type="hidden" id="companyid" name="eventInfo.companyid" />
                                
                                  <div class="box-header">
                                      <h3 class="box-title">基本信息</h3>
                                  </div><!-- /.box-header -->
                                    <div class="row box-body">
                                        <div class="col-xs-12 form-group h_90px">
                                            <label for="name">活动名称</label>
                                            <input type="text" class="form-control" id="name" name="eventInfo.name" placeholder="输入活动名称">
                                        </div>
                                        
                                        <div class="col-xs-12 form-group h_150px">
                                            <label for="memo">活动简介</label>
                                            <textarea class="form-control" id="memo" name="eventInfo.memo" rows="4" placeholder="输入活动简介"></textarea>
                                        </div>
                                    </div><!-- /.box-body -->

	                                <div class="box-header">
	                                    <h3 class="box-title">活动详情</h3>
	                                </div><!-- /.box-header -->
	                                
                                    <div class="row box-body pad">
                                        <div class="col-xs-12 form-group h_300px">
                                            <textarea class="form-control textarea" id="txt" name="eventInfoExt.txt" placeholder="输入活动详情" style="width: 100%; height: 250px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
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


        <!-- Bootstrap WYSIHTML5 -->
        <script src="${ctx }/assets/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                //bootstrap WYSIHTML5 - text editor
                $("#txt").wysihtml5();
            });
        </script>

        <script src="${ctx }/js/iEvent/biz/eventinfo/add.js" type="text/javascript"></script>

  </body>
</html>