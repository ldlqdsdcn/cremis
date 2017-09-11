/**
 * 包含easyui的扩展和常用的方法
 */

var Imi = $.extend({}, Imi);
/* 定义全局对象，类似于命名空间或包的作用 */

/**
 *
 * 取消easyui默认开启的parser
 *
 * 在页面加载之前，先开启一个进度条
 *
 * 然后在页面所有easyui组件渲染完毕后，关闭进度条
 *
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 */
$.parser.auto = true;
/*
 $(function() {
 $.messager.progress({
 text : '页面加载中....',
 interval : 100
 });
 $.parser.parse(window.document);
 window.setTimeout(function() {
 $.messager.progress('close');
 if (self != parent) {
 window.setTimeout(function() {
 try {
 parent.$.messager.progress('close');
 } catch (e) {
 }
 }, 500);
 }
 }, 1);
 $.parser.auto = true;
 });*/

/**
 * 使panel和datagrid在加载时提示
 *
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 避免验证tip屏幕跑偏
 */
var removeEasyuiTipFunction = function () {
    window.setTimeout(function () {
        $('div.validatebox-tip').remove();
    }, 0);
};
$.fn.panel.defaults.onClose = removeEasyuiTipFunction;
$.fn.window.defaults.onClose = removeEasyuiTipFunction;
$.fn.dialog.defaults.onClose = removeEasyuiTipFunction;

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 通用错误提示
 *
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function (XMLHttpRequest) {
    $.messager.progress('close');
    if (null != XMLHttpRequest.responseText && XMLHttpRequest.responseText != "") {
        $.messager.alert('报错信息', XMLHttpRequest.responseText);
    } else {
        window.location.href = "/web/login.jsp";
    }
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function (e, field) {
    e.preventDefault();
    var grid = $(this);
    /* grid本身 */
    var headerContextMenu = this.headerContextMenu;
    /* grid上的列头菜单对象 */
    if (!headerContextMenu) {
        var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
        var fields = grid.datagrid('getColumnFields');
        for (var i = 0; i < fields.length; i++) {
            var fildOption = grid.datagrid('getColumnOption', fields[i]);
            if (!fildOption.hidden) {
                $('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            } else {
                $('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            }
        }
        headerContextMenu = this.headerContextMenu = tmenu.menu({
            onClick: function (item) {
                var field = $(item.target).attr('field');
                if (item.iconCls == 'icon-ok') {
                    grid.datagrid('hideColumn', field);
                    $(this).menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-empty'
                    });
                } else {
                    grid.datagrid('showColumn', field);
                    $(this).menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-ok'
                    });
                }
            }
        });
    }
    headerContextMenu.menu('show', {
        left: e.pageX,
        top: e.pageY
    });
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function (left, top) {
    var l = left;
    var t = top;
    if (l < 1) {
        l = 1;
    }
    if (t < 1) {
        t = 1;
    }
    var width = parseInt($(this).parent().css('width')) + 14;
    var height = parseInt($(this).parent().css('height')) + 14;
    var right = l + width;
    var buttom = t + height;
    var browserWidth = $(window).width();
    var browserHeight = $(window).height();
    if (right > browserWidth) {
        l = browserWidth - width;
    }
    if (buttom > browserHeight) {
        t = browserHeight - height;
    }
    $(this).parent().css({
        /* 修正面板位置 */
        left: l,
        top: t
    });
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * panel关闭时回收内存
 */
$.fn.panel.defaults.onBeforeDestroy = function () {
    var frame = $('iframe', this);
    try {
        if (frame.length > 0) {
            for (var i = 0; i < frame.length; i++) {
                frame[i].contentWindow.document.write('');
                frame[i].contentWindow.close();
            }
            frame.remove();
            if ($.browser.msie) {
                CollectGarbage();
            }
        }
    } catch (e) {
    }
};

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
    eqPassword: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '密码不一致！'
    }
});

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 扩展datagrid，添加动态增加或删除Editor的方法
 *
 * 例子如下，第二个参数可以是数组
 *
 * datagrid.datagrid('removeEditor', 'cpwd');
 *
 * datagrid.datagrid('addEditor', [ { field : 'ccreatedatetime', editor : { type : 'datetimebox', options : { editable : false } } }, { field : 'cmodifydatetime', editor : { type : 'datetimebox', options : { editable : false } } } ]);
 *
 */
$.extend($.fn.datagrid.methods, {
    addEditor: function (jq, param) {
        if (param instanceof Array) {
            $.each(param, function (index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor;
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor: function (jq, param) {
        if (param instanceof Array) {
            $.each(param, function (index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * 扩展datagrid的editor
 *
 * 增加带复选框的下拉树
 *
 * 增加日期时间组件editor
 *
 * 增加多选combobox组件
 */
$.extend($.fn.datagrid.defaults.editors, {
    combocheckboxtree: {
        init: function (container, options) {
            var editor = $('<input />').appendTo(container);
            options.multiple = true;
            editor.combotree(options);
            return editor;
        },
        destroy: function (target) {
            $(target).combotree('destroy');
        },
        getValue: function (target) {
            return $(target).combotree('getValues').join(',');
        },
        setValue: function (target, value) {
            $(target).combotree('setValues', Imi.getList(value));
        },
        resize: function (target, width) {
            $(target).combotree('resize', width);
        }
    },
    datetimebox: {
        init: function (container, options) {
            var editor = $('<input />').appendTo(container);
            editor.datetimebox(options);
            return editor;
        },
        destroy: function (target) {
            $(target).datetimebox('destroy');
        },
        getValue: function (target) {
            return $(target).datetimebox('getValue');
        },
        setValue: function (target, value) {
            $(target).datetimebox('setValue', value);
        },
        resize: function (target, width) {
            $(target).datetimebox('resize', width);
        }
    },
    multiplecombobox: {
        init: function (container, options) {
            var editor = $('<input />').appendTo(container);
            options.multiple = true;
            editor.combobox(options);
            return editor;
        },
        destroy: function (target) {
            $(target).combobox('destroy');
        },
        getValue: function (target) {
            return $(target).combobox('getValues').join(',');
        },
        setValue: function (target, value) {
            $(target).combobox('setValues', Imi.getList(value));
        },
        resize: function (target, width) {
            $(target).combobox('resize', width);
        }
    }
});

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * @param options
 */
Imi.dialog = function (options) {
    var opts = $.extend({
        modal: true,
        onClose: function () {
            $(this).dialog('destroy');
        }
    }, options);
    return $('<div/>').dialog(opts);
};

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 *
 * @param title
 *            标题
 *
 * @param msg
 *            提示信息
 *
 * @param fun
 *            回调方法
 */
Imi.messagerConfirm = function (title, msg, fn) {
    return $.messager.confirm(title, msg, fn);
};

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 */
Imi.messagerShow = function (options) {
    return $.messager.show(options);
};

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI
 */
Imi.messagerAlert = function (title, msg, icon, fn) {
    return $.messager.alert(title, msg, icon, fn);
};

/**
 * @author Jacob
 *
 * @requires jQuery,EasyUI,jQuery cookie plugin
 *
 * 更换EasyUI主题的方法
 *
 * @param themeName
 *            主题名称
 */
Imi.changeTheme = function (themeName) {
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);

    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }

    $.cookie('easyuiThemeName', themeName, {
        expires: 7
    });
};

/**
 * @author Jacob
 *
 * 获得项目根路径
 *
 * 使用方法：Imi.bp();
 *
 * @returns 项目根路径
 */
Imi.bp = function () {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
};

/**
 * @author Jacob
 *
 * 使用方法:Imi.pn();
 *
 * @returns 项目名称(/sshe)
 */
Imi.pn = function () {
    return window.document.location.pathname.substring(0, window.document.location.pathname.indexOf('\/', 1));
};

/**
 * @author Jacob
 *
 * 增加formatString功能
 *
 * 使用方法：Imi.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 *
 * @returns 格式化后的字符串
 */
Imi.fs = function (str) {
    for (var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};

/**
 * @author Jacob
 *
 * 增加命名空间功能
 *
 * 使用方法：Imi.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
Imi.ns = function () {
    var o = {}, d;
    for (var i = 0; i < arguments.length; i++) {
        d = arguments[i].split(".");
        o = window[d[0]] = window[d[0]] || {};
        for (var k = 0; k < d.slice(1).length; k++) {
            o = o[d[k + 1]] = o[d[k + 1]] || {};
        }
    }
    return o;
};

/**
 *
 * 生成UUID
 *
 * @returns UUID字符串
 */
Imi.random4 = function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
Imi.UUID = function () {
    return (Imi.random4() + Imi.random4() + "-" + Imi.random4() + "-" + Imi.random4() + "-" + Imi.random4() + "-" + Imi.random4() + Imi.random4() + Imi.random4());
};

/**
 * @author Jacob
 *
 * 获得URL参数
 *
 * @returns 对应名称的值
 */
Imi.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
};

/**
 * @author Jacob
 *
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 *
 * @returns list
 */
Imi.getList = function (value) {
    if (value != undefined && value != '') {
        var values = [];
        var t = value.split(',');
        for (var i = 0; i < t.length; i++) {
            values.push('' + t[i]);
            /* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

/**
 * @author Jacob
 *
 * @requires jQuery
 *
 * 判断浏览器是否是IE并且版本小于8
 *
 * @returns true/false
 */
Imi.isLessThanIe8 = function () {
    return ($.browser.msie && $.browser.version < 8);
};

/**
 * @author Jacob
 *
 * @requires jQuery
 *
 * 将form表单元素的值序列化成对象
 *
 * @returns object
 */
Imi.serializeObject = function (form) {
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

/**
 *
 * 将JSON对象转换成字符串
 *
 * @param o
 * @returns string
 */
Imi.jsonToString = function (o) {
    var r = [];
    if (typeof o == "string")
        return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
    if (typeof o == "object") {
        if (!o.sort) {
            for (var i in o)
                r.push(i + ":" + obj2str(o[i]));
            if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                r.push("toString:" + o.toString.toString());
            }
            r = "{" + r.join() + "}";
        } else {
            for (var i = 0; i < o.length; i++)
                r.push(obj2str(o[i]));
            r = "[" + r.join() + "]";
        }
        return r;
    }
    return o.toString();
};

/**
 * @author 郭华(夏悸)
 *
 * 格式化日期时间
 *
 * @param format
 * @returns
 */
Date.prototype.format = function (format) {
    if (isNaN(this.getMonth())) {
        return '';
    }
    if (!format) {
        format = "yyyy-MM-dd hh:mm:ss";
    }
    var o = {
        /* month */
        "M+": this.getMonth() + 1,
        /* day */
        "d+": this.getDate(),
        /* hour */
        "h+": this.getHours(),
        /* minute */
        "m+": this.getMinutes(),
        /* second */
        "s+": this.getSeconds(),
        /* quarter */
        "q+": Math.floor((this.getMonth() + 3) / 3),
        /* millisecond */
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

/**
 * @requires jQuery
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
    cache: false,
    complete: function (XHR, TS, s, e) {
        var val = true;
        var resText = XHR.responseText;
        if (null != resText && resText != "" && resText != undefined) {
            //超时
            var obj = resText;
            if (null != obj && obj != "" && obj != undefined) {
                if (obj == "{sessionState:0}") {
                    window.location.href = 'login.jsp';
                    val = false;
                }
            }
        }
        return val;
    }
});
/**
 * 刷新菜单节点树信息
 */
Imi.treeReload = function (node) {
    $('#' + node).tree('reload');
};
/**
 *处理Window取消按钮
 */
Imi.winclose = function (obj) {
    obj.dialog('destroy');
};


/**
 * 将String to json
 */
Imi.stringTojson = function (data) {
    var obj = eval('(' + data + ')');
    return obj;
};
/**
 * 将String to json
 */
Imi.isNotVal = function (val) {
    var result = false;
    if (null != val && val != "") {
        result = true;
    }
    return result;
};
/**
 * 获得选中的对象集合
 */
Imi.getChecked = function (obj) {
    var rows = obj.datagrid('getChecked');
    var ids = [];
    if (rows.length == 1) {
        return rows[0];
    }
    return null;
};
/**
 * 设置选中值
 * @param {} setV
 * @param {} e
 * @param {} v
 * @param {} s
 * @return {}
 */
Imi.setCheckedValue = function (setV, e, v, s) {
    var leg = e.length;
    if (leg > 0) {
        return e[0];
        //oot.combobox('setValue',e[0].lablebbname)
    }
    return null;
};
/**
 * setCheckedRadio
 * @param {} id
 * @param {} val
 */
Imi.setCheckedRadio = function (id, val) {
    var getStatus = val;
    if (getStatus == 0 && Imi.isNotVal(val)) {
        $("#" + id + "_0").attr("checked", "checked");
    } else {
        $("#" + id + "_1").attr("checked", "checked");
    }
};
/**
 *处理选中按钮
 *objid:对象编号
 *val:按钮对象集合
 */
Imi.setDgChange = function (objid, val) {

    var rows = $("#" + objid).datagrid('getChecked');
    var str = val;
    var n = rows.length || 0;
    if (n != 1) {
        $('#' + str[1]).linkbutton({disabled: true});
    } else {
        $('#' + str[1]).linkbutton({disabled: false});
    }
    if (n > 0) {
        $('#' + str[0]).linkbutton({disabled: true});
        $('#' + str[2]).linkbutton({disabled: false});
    } else {
        $('#' + str[0]).linkbutton({disabled: false});
        $('#' + str[2]).linkbutton({disabled: true});
    }
};
/**
 * 验证密码和重新输入密码一致.
 */
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '输入的两次密码必须一致.'
    }
});
/**
 * 时间格式转化
 */
Imi.getformatDate = function (obj) {
    var Ndate = $.formatDate(obj, 'yyyy-M-d HH:mm:ss');
    return Ndate;
};

//进度框
function showProcess(isShow, title, msg) {
    if (!isShow) {
        $.messager.progress('close');
        return;
    }
    var win = $.messager.progress({
        title: title,
        msg: msg
    });
}
//Ajax提交交互事件信息
function imiAjaxDg(actionName, paramsId, Dg) {
    $.ajax({
        url: ctx + actionName,
        data: {
            paramsId: paramsId
        },
        beforeSend: function (XMLHttpRequest) {
            showProcess(true, '温馨提示', '正在提交数据...');
        },
        success: function (data) {

            var result = Imi.stringTojson(data);
            if (result.flag) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>" + result.error + "</font>"
                });
                Dg.datagrid("load");
            } else {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=red>" + result.error + "</font>"
                });
            }
            return true;
        },
        complete: function (XMLHttpRequest, textStatus) {
            showProcess(false);
        },
        error: function () {
            showProcess(false);
            $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
        }

    });
    return false;
}
//Ajax提交交互事件信息
function imiAjaxMapDg(actionName, paramsId, paramsIdn, Dg) {
    $.ajax({
        url: ctx + actionName,
        data: {
            paramsId: paramsId,
            paramsnumId: paramsIdn
        },
        beforeSend: function (XMLHttpRequest) {
            showProcess(true, '温馨提示', '正在提交数据...');
        },
        success: function (data) {

            var result = Imi.stringTojson(data);
            if (result.flag) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>" + result.error + "</font>"
                });
                Dg.datagrid("load");
            } else {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=red>" + result.error + "</font>"
                });
            }

            return true;
        },
        complete: function (XMLHttpRequest, textStatus) {
            showProcess(false);
        },
        error: function () {
            showProcess(false);
            $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
        }

    });
    return false;
}
//ui from 提交事件
function imiSumbitFrom(uiFrom, actionName, win, refDg) {
    uiFrom.form('submit', {
        url: ctx + actionName,
        onSubmit: function () {
            var flag = $(this).form('validate');
            if (flag) {
                showProcess(true, '温馨提示', '正在提交数据...');
            }
            return flag
        },
        success: function (data) {
            showProcess(false);
            var result = $.parseJSON(data);
            if (result.flag) {
                Imi.winclose(win);
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>" + result.error + "</font>"
                });
                if (Imi.isNotVal(refDg)) {
                    refDg.datagrid("load");
                }
            } else {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=red>" + result.error + "</font>"
                });
            }
        },
        onLoadError: function () {
            showProcess(false);
            $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
        }
    });
}
/****
 * 影片编辑方法 =begin
 * @param {} id
 */
movieRfg = function (id) {
    $.ajax({
        url: ctx + "/movie/findMoviebbInfoListsByMTId.action?paramsId=" + id,
        beforeSend: function (XMLHttpRequest) {
            showProcess(true, '温馨提示', '正在提交数据...');
        },
        success: function (data) {
            if (Imi.isNotVal(data)) {
                var result_rows = $.parseJSON(data);
                var tr = "";
                var getTbody = $("#tbody_ids");
                getTbody.empty();
                $.each(result_rows, function (i, options) {
                    var cell_i = i + 1;
                    var get_movieid = result_rows[i].movieid;
                    var get_moviebbid = result_rows[i].moviebbid;
                    var get_movieqtr = result_rows[i].movieqtr;
                    var get_mlanguage = result_rows[i].mlanguage;
                    var get_sorts = result_rows[i].sorts;
                    var get_moviehd = result_rows[i].moviehd;

                    tr += '<tr id="tr_' + cell_i + '" width="50px;">';
                    tr += '<td align="center" class="td_center" width="50px;" title="' + get_moviebbid + '">' + get_movieqtr + '</td>';
                    tr += '<td align="center" class="td_center">' + get_mlanguage + '</td>';
                    tr += '<td align="center" class="td_center">' + get_sorts + '</td>';
                    tr += '<td align="center" class="td_center">' + get_moviehd + '</td>';
                    tr += '<td align="center" class="td_center" >' +
                    '<span onclick="cellActdelTr(\'' + get_moviebbid + '\')" class="del_style">删除</span>&nbsp|&nbsp<span onclick="cellActeditTr(' + cell_i + ')" class="edit_style">修改</span></td>';
                    tr += '</tr>';
                });

                getTbody.append(tr);
            }
            return true;
        },
        complete: function (XMLHttpRequest, textStatus) {
            showProcess(false);
        },
        error: function () {
            showProcess(false);
            $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
        }

    });
};

//删除方法
function cellActdelTr(deltr) {
    $.ajax({
        url: ctx + "/movie/deleteMoviebbInfoById.action",
        data: {
            paramsId: deltr
        },
        beforeSend: function (XMLHttpRequest) {
            showProcess(true, '温馨提示', '正在提交数据...');
        },
        success: function (data) {

            var result = Imi.stringTojson(data);
            if (result.flag) {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=blue>" + result.error + "</font>"
                });
                var gMTID = $("#movie_select_cell_bb_id").val();
                movieRfg(gMTID);
            } else {
                parent.Imi.messagerShow({
                    title: '提示',
                    msg: "<font color=red>" + result.error + "</font>"
                });
            }
            return true;
        },
        complete: function (XMLHttpRequest, textStatus) {
            showProcess(false);
        },
        error: function () {
            showProcess(false);
            $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
        }

    });
}
//编辑行
function cellActeditTr(aObject) {
    //选中行
    $("#movie_celltr_id").val(aObject);
    var editLableBBWin = parent.Imi.dialog({
        title: '影片季集',
        href: ctx + "/webimi/app/movie/MovieBBInfoWin.jsp",
        width: 510,
        height: 329,
        resizable: true,
        buttons: [{
            text: '确认',
            iconCls: 'icon-save',
            handler: function () {
                var fb = editLableBBWin.find('form');
                fb.form('submit', {
                    url: ctx + "/movie/editMoviebbInfo.action",
                    onSubmit: function () {
                        var flag = $(this).form('validate');
                        if (flag) {
                            showProcess(true, '温馨提示', '正在提交数据...');
                        }
                        return flag
                    },
                    success: function (data) {
                        showProcess(false);
                        var result = $.parseJSON(data);
                        if (result.flag) {
                            Imi.winclose(editLableBBWin);
                            parent.Imi.messagerShow({
                                title: '提示',
                                msg: "<font color=blue>" + result.error + "</font>"
                            });
                            var gMTID = $("#movie_select_cell_bb_id").val();
                            movieRfg(gMTID);
                        } else {
                            parent.Imi.messagerShow({
                                title: '提示',
                                msg: "<font color=red>" + result.error + "</font>"
                            });
                        }
                    },
                    onLoadError: function () {
                        showProcess(false);
                        $.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
                    }
                });
                Imi.winclose(editLableBBWin);
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                Imi.winclose(editLableBBWin);
            }
        }]
    });
}
//end
//返回图片
function resultOnlineImg(val) {
    var result = "<img src='" + ctx + "/images/app/icon/gq.png'/>";
    if (Imi.isNotVal(val)) {
        var img_s = val.indexOf(".");
        if (img_s > 0) {
            result = "<img src='" + ctx + "/images/app/icon/cardurl.png'/>";
        }
    }
    return result;
}
//回调处理发布信息图片问题
srcretrun = function (row) {
    var val = row.remarks;
    var result = "";
    if (null != val && val != "null") {
        var rv = val.length;
        if (rv > 60) {
            result = "<img src='" + ctx + "/images/app/icon/post.gif' /><font color=red>...</font>";
        } else {
            result = val;
        }
    }
    return result;
};
//SORTS 最大值
getSortsFn = function (obj_) {
    var get_total = $("#" + obj_).datagrid('getData').total + 1;
    return get_total;
};
//SEX 映射返回值
function getSexFn(sex) {
    var result = "<font color=blue>男</font>";
    if (sex == 0) {
        result = "<font color=red>女</font>";
    }
    return result;
}
//Status 映射返回值
function getStatusFn(status) {
    var result = "<font color='blue'>生效</font>";
    if (status == 0) {
        result = "<font color='red'>失效</font>";
    }
    return result;
}
//处理字符串
function fixString(str,start,end,fix){
    if(start<end){
        var pre='',next='',sub = false;
        if(start>0){
            pre = fix;
            sub = true;
        }
        if(str.length>=end){
            next = fix;
            sub = true;
        }
        if(sub){
            str = str.substr(start,end);
        }
        if(start>0){
            str = fix + str + next;
        }
        return str;

    }
}


/**
 * 省略小数
 * @param number    原数值
 * @param n 保留位数
 */
function roundNumber(number,n){
    if(!n&&n!=0){
       n=2;
    }else if(n<0){
        n=0;
    }
    var pow = 1;
    for(var i=0;i<n;i++){
        pow*=10;
    }
    return Math.round(number*pow)/pow;
}

function numberToRMB(number){
    return "￥"+roundNumber(number,2)+"元";
}

//回调处理发布信息
innerResult = function (remarks) {
    var val = remarks;
    var result = "";
    if (null != val && val != "null") {
        var rv = val.length;
        if (rv > 60) {
            result = "<img src='" + ctx + "/images/app/icon/post.gif' /><font color=red>...</font>";
        } else {
            result = val;
        }
    }
    return result;
};

