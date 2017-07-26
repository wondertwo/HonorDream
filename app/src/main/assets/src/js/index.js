/**
 * Created by CM on 2017/7/23.
 */
$(function() {
    //var json = JSON.parse(window.android.getJSON());
    //var container = $('.near-panel .list');
    //container.html(json);
    var nearPanel = {
        rankList: function(data) {
            var container = $('.near-panel .list');
            var html = '';
            for(var i=0, len = data.length; i<len ; i++) {
                var curItem = data[i];
                html += '' +
                    '<li>' +
                    '   <a href="http://www.liebao.cn/">' +
                    '       <div id="user-info">' +
                    '           <img src="' + curItem.via + '" alt="用户头像">' +
                    '           <div>' +
                    '               <span>' + curItem.username + '</span>' +
                    '               <span class="near-distance">距离：' + curItem.dis + '米</span>|<span class="near-duration">' + curItem.time + '分钟前来过</span>' +
                    '           </div>' +
                    '       </div>' +
                    '       <div id="goods-show">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '       </div>' +
                    '       <div id="goods-describe">' + curItem.describe + '</div>' +
                    '   </a>' +
                    '</li>'
            }

            container.html(html);
        }
    };

    var recommendPanel = {
        rankList: function(data) {
            var container = $('.recommend-panel .list');
            var html = '';
            for(var i=0, len = data.length; i<len ; i++) {
                var curItem = data[i];
                html += '' +
                    '<li>' +
                    '   <a href="#">' +
                    '       <div id="user-info">' +
                    '           <img src="' + curItem.via + '" alt="用户头像">' +
                    '           <div>' +
                    '               <span>' + curItem.username + '</span>' +
                    '               <span class="recommend-distance">距离：' + curItem.dis + '米</span>|<span class="recommend-duration">' + curItem.time + '分钟前来过</span>' +
                    '           </div>' +
                    '       </div>' +
                    '       <div id="goods-show">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '           <img src="' + curItem.img + '" alt="外星人电脑">' +
                    '       </div>' +
                    '       <div id="goods-describe">' + curItem.describe + '</div>' +
                    '   </a>' +
                    '</li>'
            }
            container.html(html);
        }
    };


    function initNearPanel() {
        //var api = 'http://127.0.0.1:8080/data/info.json';
        var api = './data/info.json';

        var data = {};
        $.ajax({
            type: 'GET',
            url: api,
            data: data,
            dataType: 'json',
            success: function (res) {
                console.log(res.users);
                nearPanel.rankList(res.users);
            },
            error: function (err) {
                console.error(err);
            }
        });
    }

    function initRecommendPanel() {
        var api = './data/info.json';

        var data = {};
        $.ajax({
            type: 'GET',
            url: api,
            data: data,
            dataType: 'json',
            success: function (res) {
                console.log(res.users);
                recommendPanel.rankList(res.users);
            },
            error: function (err) {
                console.error(err);
            }
        });
    }



    /* tab切换样式的变化 */
    function initPage() {
        var isTabPanelInited = [];
        window.currentPanelIndex = 0;
        tabs.selected($('#tabs li'), $('.tabs-panel'), function (index) {
            window.currentPanelIndex = index;
            if (isTabPanelInited[index]) {
                return;
            }
            switch (index) {
                case 0:
                    initNearPanel();
                    isTabPanelInited[index] = true;
                    break;
                case 1:
                    initRecommendPanel();
                    isTabPanelInited[index] = true;
                    break;
            }
        });
        $('#tabs li').eq(0).click();


    }

    initPage();

    $(window).scroll(function() {
        var oTab = $('#tabs');
        var hasClassName = oTab.is('.floating');
        var top = $(window).scrollTop();
        console.log(top);
        if(top >= 220) {
            if(!hasClassName) {
                oTab.addClass('floating');
            } else {
                return;
            }
        } else {
            if(hasClassName) {
                oTab.removeClass('floating');
            }
        }

    })
});
