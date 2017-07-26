/**
 * Created by CM on 2017/7/24.
 */
/*
* tab页面切换
* */
var tabs = {
    selected: function (trigger, panel, callback) {
        trigger.click(function() {
            var _this = $(this);
            var index = _this.index();

            _this.addClass('selected').siblings().removeClass('selected');
            panel.eq(index).show().siblings().hide();
            callback && callback(index);
        });
    }
}
