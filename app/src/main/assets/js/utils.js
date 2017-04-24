define(['framebj-util'], function(api) {
    // 直接对返回的对象附加属性和方法，实现继承效果
    // u-insert#debug-config#
	api.debug = false;//当为true时，使用的是测试json数据与地址，后续修改为false
    // u-insert#
    api.path = '/hwl-web';
    api.paths = '/hwl/bootstrap';
    // 文件服务
    api.fileser = '/fileser/cms/upload'; //文件上传路径
    api.fileserDownload = '/fileser/download/'; // 文件下载路径
    api.fileserImage = '/fileser/image/'; // 图片下载路径
    api.fileserPreview = '@128w_128h_80Q'; // 文件预览的配置
    api.syscode = 'hwl'; //文件上传syscode
    // 数据字典
    api.dictUrl = '/hwl-base-web/dict/tree'; // 数据字典路径
    api.portalKey = 'hwl';
    api.dictType = 'hwl';
    api.pageRoot = 'bootstrap';
    api.ueditorHomeUrl = '/framebj/thirdparty/UEditor/1.4.3.1/';

    api.modalHeight = 400; //模态框的高度
    //api.baseUrl = "/mmap-web/tile/googleSer?LAYER=xjty&z={z}&x={x}&y={y}";

    // u-insert#param_other1#
    // u-insert#

    var urls = {
        // u-insert#urls_other1#
        // u-insert#
        // 通用接口
        create: '/crud/${0}/create',
        update: '/crud/${0}/update',
        readById: '/crud/${0}/readById',
        count: '/crud/${0}/count',
        readList: '/crud/${0}/readList',
        readTree: '/crud/${0}/readTree',
        readAndCount: '/crud/${0}/readAndCount',
        logicDelMult: '/crud/${0}/logicDelMult',
        delModel: '/crud/${0}/delModel',
        exportAsExcle: '/crud/${0}/exportAsExcle',
        exportTemplateAsExcle: '/crud/${0}/exportTemplateAsExcle',
        importAsExcle: '/crud/${0}/importAsExcel'

        //其他接口
        // u-insert#urls_other#
        // u-insert#
    }

    // 获取url，para为数组
    api.getUrl = function(key, para) {
        var url = this.mix(this.debug ? (this.paths + test_urls[key]) : (this.path + urls[key]), para);
        if (top.ctrl && top.ctrl.appendToken) {
            return top.ctrl.appendToken(url);
        }
        return url;
    }

    // 初始化数据字典
    api.initTranslator = function(elements) {
        $.translator.init($.extend({
            // 渲染完成事件
            onRendered: function() {
                $.each(elements, function(index, obj) {
                    if (obj.renderedDefValue) {
                        $(obj.selector).val(obj.renderedDefValue);
                    }
                });
            },
            elements: elements || [],
            queryParams: { // 附加的查询参数
                portalKey: api.portalKey
            },
            defaults: {
                dictType: api.dictType // 默认dictType
            }
        }, self == top ? {
            url: api.dictUrl,
            root: 'data' // 表示查询返回的数据从data这个节点获取值
        } : {}));
    }


    // firstword to uppercase
    api.toUpperCaseFirstWord = function(str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // firstword to lowercase
    api.toLowerCaseFirstWord = function(str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    // 初始化文件模态框
    api.fileModalDialogAlreadyInit = false;
    api.initFileModalsDialog = function(params) {
        if (!params || params.length == 0) return;

        if (!api.fileModalDialogAlreadyInit) {
            var initparam = [];
            $.each(params, function(index, value) {
                var id = value.id;
                //var callback = value.cb
                var title = value.title;
                var jobj = value.jobj;

                jobj.before('&nbsp;&nbsp;<span class="show_file_count">[<span id="form_fileupload_btn_' + id + '_count"></span>]</span><span class="btn btn-warning btn-sm" style="display:none;" id="form_fileupload_btn_' + id + '">编辑</span>');
                jobj.attr('readonly', 'readonly');
                jobj.attr('placeholder', '点击上传');

                if (value.fileMaxCount == 1) {
                    $(".show_file_count").hide();
                }

                initparam.push({
                    id: 'fileUploadModelDialog_' + id,
                    title: title,
                    html: '<ul class="list-inline custom-upload-imgwrap fileuploadRetValue_' + id + '"></ul><input class="form_fileupload_input_' + id + '" name="file" type="file" multiple><span id="fileUploadErrorMsg_' + id + '" ></span>', // html内容
                    buttons: [{
                        text: '完成',
                        theme: 'info',
                        handler: function() {
                            // 文件上传的路径设置
                            var filePaths = [];
                            var fileCount = 0;
                            $('.fileuploadRetValue_' + id + '>li>img').each(function() {
                                fileCount++;
                                filePaths.push($(this).data('key'));
                            })
                            var str = filePaths.join(',');
                            //callback.call(this, str);
                            jobj.val(str);

                            $('#form_fileupload_btn_' + id + '_count').html('有' + fileCount + '个文件');
                            $.customModal.close('fileUploadModelDialog_' + id);
                        }
                    }]
                });
            });

            $.customModal.add(initparam);

            for (var i = 0; i < params.length; i++) {
                (function(param) {
                    var id = param.id;
                    var filepaths = param.filepaths;
                    var allowedFileExtensions = param.allowedFileExtensions;
                    var fileIcon = param.fileIcon;
                    var fileMaxCount = param.fileMaxCount;

                    // upload
                    $('#form_fileupload_btn_' + id).on('click', function(e) {
                        $.customModal.open('fileUploadModelDialog_' + id);
                    });
                    $('#form_fileupload_btn_' + id + '_count').on('click', function(e) {
                        $.customModal.open('fileUploadModelDialog_' + id);
                    });
                    param.jobj.on('click', function(e) {
                        $.customModal.open('fileUploadModelDialog_' + id);
                    });

                    // 文件上传，多个文件分开写
                    $('.form_fileupload_input_' + id).fileinput({
                        language: 'zh',
                        uploadUrl: api.fileser, // 上传地址
                        uploadExtraData: { // 额外参数
                            syscode: api.syscode
                        },
                        showPreview: false,
                        elErrorContainer: '#fileUploadErrorMsg_' + id,
                        maxFileCount: fileMaxCount,
                        //                  showUpload: false,//上传按钮
                        //                  showRemove: false,//移除按钮
                        dropZoneEnabled: false, // 隐藏拖拽区域，要实现文件拖拽效果可设为true
                        allowedFileExtensions: allowedFileExtensions, // 支持的文件后缀
                        // allowedFileTypes: ['image', 'html', 'text', 'video', 'audio', 'flash', 'object'], // 支持的文件类型
                        ajaxSettings: { // ajax配置
                            success: function(ret) {
                                if (ret == null || !ret.success) {
                                    top.ctrl.failHandle(ret);
                                    return;
                                }

                                var alreadyFileCount = $('.fileuploadRetValue_' + id).length;
                                if (alreadyFileCount >= param.fileMaxCount) {
                                    $('.fileuploadRetValue_' + id + '>li:first-child').remove();
                                }

                                var imgLi = [];
                                $.each(ret.data, function(index, obj) {
                                    var imgLi1 = ['<li><img onclick="window.open(\'' + api.fileserImage + obj.key + '\');" data-key="',
                                        //api.fileserImage + obj.key,//fileser读取
                                        obj.uri,//url读取
                                        '" src="',
                                        //(fileIcon ? fileIcon : api.fileserImage + obj.key + api.fileserPreview),//fileser读取
                                        (fileIcon ? fileIcon : obj.uri + api.fileserPreview),//url读取
                                        '"><div class="custom-upload-tool">',
                                        '<a href="javascript:;" data-target="delete"><span class="glyphicon glyphicon-trash"></span></a>',
                                        '</div></li>'
                                    ].join('');
                                    imgLi.push(imgLi1);
                                });
                                $('.fileuploadRetValue_' + id).append(imgLi.join(''));
                                //关闭上传控件
                                //$('.close.fileinput-remove').attr('disabled', false).trigger('click');
                                $('.form_fileupload_input_' + id).fileinput('clear');

                            },
                            error: function(e) {
                                console.log('error', e);
                                alert("文件上传出错");
                            }
                        }
                    });

                    // 图片删除
                    $('.fileuploadRetValue_' + id).on('click', '.custom-upload-tool a', function(e) {
                        if (!confirm('是否删除？')) {
                            return;
                        }
                        e.preventDefault();
                        var me = $(this);
                        switch (me.data('target')) {
                            case 'delete':
                                var index = me.parent().parent().index();
                                $('.fileuploadRetValue_' + id).children('li').eq(index).remove();
                                break;
                        }
                    });

                })(params[i]);
            }

            api.fileModalDialogAlreadyInit = true;
        }
        for (var i = 0; i < params.length; i++) {
            (function(param) {
                var id = param.id;
                var filepaths = param.filepaths;
                var fileIcon = param.fileIcon;


                //初始化默认值
                if (filepaths && filepaths != "") {
                    $('.fileuploadRetValue_' + id).html('');

                    var filepath = filepaths.split(",");
                    var fileCount = 0;
                    $.each(filepath, function(index, value) {
                        if (value && value != "") fileCount++;
                        else return;

                        var imgLi = ['<li><img onclick="window.open(\'' + value + '\');" data-key="',
                            value,
                            '" src="',
                            (fileIcon ? fileIcon : value + api.fileserPreview),
                            '"><div class="custom-upload-tool">',
                            '<a href="javascript:;" data-target="delete"><span class="glyphicon glyphicon-trash"></span></a>',
                            '</div></li>'
                        ].join('');
                        $('.fileuploadRetValue_' + id).append(imgLi);
                    });
                    $('#form_fileupload_btn_' + id + '_count').html('有' + fileCount + '个文件');
                } else {
                    $('#form_fileupload_btn_' + id + '_count').html('有0个文件');
                }


            })(params[i]);
        }
    }
    
    api.filesIcon = function(className) {
    	if(!className) {
    		return '';
    	}
        return api.paths + "/img/filesIcon/icon_"+className+".png";
    }
    // u-insert#other-function#
    // u-insert#
    return api;
});
