define(function() {
    require.config({
        baseUrl: '/hwl-app/js',
        paths: {
            'jquery': '/framebj/thirdparty/jquery/1.11.1/jquery.min',
            'framebj-util': '/framebj/build/util/1.0/util',
            'ichain': '/framebj/build/ichain/1.0/ichain.min',
            'translator': '/framebj/build/translator/1.0/translator',
            'bootstrap': '/framebj/thirdparty/bootstrap/3.3.4/js/bootstrap.min',
            'custom-tabs': '/framebj/build/custom/custom-tabs',
            'custom-modal': '/framebj/build/custom/custom-modal',
            'custom-grid': '/framebj/build/custom/custom-grid',
            'page': '/framebj/thirdparty/createPage/1.0/jquery.createPage',
            'WdatePicker': '/framebj/thirdparty/WdatePicker/4.7/WdatePicker',
            'fileinput_core': '/framebj/thirdparty/fileinput/4.2.1/js/fileinput.min',
            'fileinput': '/framebj/thirdparty/fileinput/4.2.1/js/fileinput_locale_zh',
            'ztree': '/framebj/thirdparty/zTree/3.5.17/js/jquery.ztree.core-3.5.min',
            'ztree-excheck': '/framebj/thirdparty/zTree/3.5.17/js/jquery.ztree.excheck-3.5.min',
            'ztree-exedit': '/framebj/thirdparty/zTree/3.5.17/js/jquery.ztree.exedit-3.5.min',
            'multiselect': '/framebj/thirdparty/multiselect/2.0.0/js/multiselect.min',
            'zeroclipboard': '/framebj/thirdparty/UEditor/1.4.3.1/third-party/zeroclipboard/ZeroClipboard.min',
            'ueconfig': '/framebj/thirdparty/UEditor/1.4.3.1/ueditor.config',
            'ueditor': '/framebj/thirdparty/UEditor/1.4.3.1/ueditor.all.min',
            'validform': '/framebj/thirdparty/Validform/5.3.2/Validform.min'
            // u-insert#other_paths#
            // u-insert#
        },
        // 配置依赖关系
        shim: {
            'translator': {
                deps: ['jquery']
            },
            'bootstrap': {
                deps: ['jquery']
            },
            'page': {
                deps: ['jquery']
            },
            'custom-grid': {
                deps: ['jquery']
            },
            'custom-modal': {
                deps: ['jquery']
            },
            'bootstrap-datetimepicker': {
                deps: ['bootstrap']
            },
            'fileinput_core': {
                deps: ['bootstrap']
            },
            'fileinput': {
                deps: ['fileinput_core']
            },
            'ztree': {
                deps: ['jquery']
            },
            'ztree-excheck': {
                deps: ['ztree']
            },
            'ztree-exedit': {
                deps: ['ztree']
            },
            'multiselect': {
                deps: ['bootstrap']
            },
            'validform': {
                deps: ['jquery']
            },
            'ueditor': {
                exports: 'UE'
            },
        }
    });
});