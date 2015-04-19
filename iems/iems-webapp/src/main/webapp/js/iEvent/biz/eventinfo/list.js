/**
 * iEvent.biz.eventinfo.list.js
 */

$(function() {
	
	var sVersion = "v1";
	var sResource = "eventInfos";
	
	var companyid = iEvent.current_user_companyid;

	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			window.location.href = iEvent.host+'/error.do';
		});
		return;
	}
	
	var sAjaxSource = iEvent.api_url(['',sVersion,sResource].join('/'), {'companyid': companyid});
	
	var oSettings = $.extend({}, iEvent.DataTable.defaults, {
		"aoColumns": [
			{ "mData": "companyInfo.name" },
			{ "mData": "eventInfo.name" },
			{ "mData": "eventInfo.memo" },
			{ "mData": "eventInfo.id", 
			  "mRender": function ( data, type, full ) {
				var html = "";
				html += '<a href="#" class="btn btn-sm btn-primary" data-src="edit.do?id='+data+'" data-toggle="modal" data-target="#modal" data-title="修改活动">修改</a>';
				html += '&nbsp;&nbsp;';
				html += '<a href="#" class="btn btn-sm btn-danger" data-pk="'+data+'" data-trigger="confirm" data-action="confirm_yes" data-keyboard="true" data-cssclass="confirm_modal" data-content="确定要删除吗？" data-yes="确定" data-cancel="取消" data-target="#confirm_'+data+'">删除</a>';
				html += '<br />';
				html += '<a href="../eventStaff/list.do?companyid='+companyid+'&eventid='+data+'" >工作人员</a>';
				html += '&nbsp;&nbsp;';
				html += '<a href="../eventPersonnel/list.do?companyid='+companyid+'&eventid='+data+'" >参会人员</a>';
				html += '&nbsp;&nbsp;';
				html += '<a href="../eventTodo/list.do?companyid='+companyid+'&eventid='+data+'" >工作安排</a>';
				html += '&nbsp;&nbsp;';
				html += '<a href="../eventSchedule/list.do?companyid='+companyid+'&eventid='+data+'" >日程安排</a>';
		        return html;
		      }
			}
		],
		"sAjaxSource": sAjaxSource
	});
	
	var oTable = $('#table').dataTable(oSettings);
	
	var oModel = $('#modal');
	oModel.on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  //var pk = button.data('pk') // Extract info from data-* attributes
	  var title = button.data('title');
	  //alert(button.attr("href"));
	  //alert(pk);
	  var src = button.data("src");
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  var modal = $(this)
	  modal.find('.modal-title').text(title)
	  modal.find('.modal-body iframe').attr({"src": src});
	});
	oModel.on('hidden.bs.modal', function (event) {
	  var modal = $(this)
	  modal.find('.modal-body iframe').attr({"src": ""});
	});
	
	var fnDeleteCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				window.location.reload();
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}
	};

	window["confirm_yes"] = function(options) {
		var sId = options.pk;
		
		sDeleteUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/')); 
		
		iEvent.deleet(sDeleteUrl, {}, fnDeleteCallback);
		
		return false;
	};
	
});
