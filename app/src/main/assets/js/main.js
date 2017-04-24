define([
    'jquery',
    'utils',
    'ichain',
    'bootstrap',
    'translator',//数据字典插件
    //树插件
    'ztree',
    'ztree-excheck',
    'ztree-exedit'
], function($, utils) {

    var api = {};

    // 程序入口
    api.start = function() {
        //1:数据字典
        initTranslator();
        //2:树
        initTree();
    }

    // 初始化数据字典控件
    function initTranslator() {
        utils.initTranslator([
        {
              dictType: 'sc', //可以配置单独的dictType
              selector: '[dict_sex]',
              classId: 'sex'  // 内容类型
              // 可以为下拉框配置默认值
              ,defaultValue: -1  // 内容类型,
              ,defaultText: '请选择'
        },
        {
              dictType: 'hwl', //可以配置单独的dictType
              selector: '[dict_degree]',
              classId: 'degree'  // 内容类型
              // 可以为下拉框配置默认值
              ,defaultValue: -1  // 内容类型,
              ,defaultText: '请选择'
        },
         {
              dictType: 'hwl', //可以配置单独的dictType
              selector: '[dict_salary_exp]',
              classId: 'salary_exp'  // 内容类型
              // 可以为下拉框配置默认值
              ,defaultValue: -1  // 内容类型,
              ,defaultText: '请选择'
        },
        {
              dictType: 'hwl', //可以配置单独的dictType
              selector: '[dict_postClassify]',
              classId: 'postClassify'  // 内容类型
              // 可以为下拉框配置默认值
              ,defaultValue: -1  // 内容类型,
              ,defaultText: '请选择'
        },
        ]);
    }

    // 初始化树组件
    function initTree() {
        //树配置：zTree -- jQuery 
        var setting = {
            view: {
                // showLine: false, // 是否显示连接线
                showIcon: false // 是否显示图标
            },
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true, // 启用简单json数据
                    idKey: 'seqId',
                    pIdKey: 'superId',
                    rootPId: 0
                },
                key: {
                    name: 'name',
                    url: 'xUrl'
                }
            },
            callback: {
                //onCheck: zTreeOnCheck,
                onClick: zTreeOnClick,
                // onExpand: zTreeOnExpand
            }
        };

        var zNodes = [];

        $.ajax({
            url: utils.getUrl('readTree', ['hwlRegion']),
            type: 'POST',
            data: {
                // 'tree_deep': 2,//查询深度
            },
            success: function(ret) {
                if (!ret || !ret.success) {
                    return;
                }

                $.each(ret.data, function(i, o) {
                    // isLeaf 是否叶子节点 1-是，2-否
                    o.modelObj['isParent'] = (!o.modelObj || !o.modelObj.isLeaf || o.modelObj.isLeaf == 2);
                    o.modelObj['open'] = false;//是否展开
                    zNodes.push(o.modelObj);
                });
                
                var treeObj = $.fn.zTree.init($("#myTree"), setting, zNodes);
            },
            error: function(e) {
                console.log('error', e);
                alert('出错了');
            }
        });

    }


     function zTreeOnClick(event, treeId, treeNode) {
        console.log("treeNode",treeNode);
    }

    return api;
});