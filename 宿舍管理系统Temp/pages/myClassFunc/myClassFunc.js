const app = getApp()
Page({
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    Custom: app.globalData.Custom,
    TabCur: 0,
    MainCur: 0,
    VerticalNavTop: 0,
    list: [],
    load: true
  },
  onLoad() {
    var that=this;
    wx.request({
      url: 'http://localhost:8080/MyClassPageController/getClassNameList',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
           
        },
        success:function(res){
            var resList = res.data;
            console.log(resList)
            let list = [{}];
            for (let i = 0; i < resList.length; i++) {
              list[i] = {};
              list[i].name =  resList[i];
              list[i].id = i;
              console.log("-=-=-=-=:  "+list[i].name)
            }
            that.setData({
              list:list,
              listCur: list[0]
            })

  
           
        }
    });
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    
  },
  onReady() {
    wx.hideLoading()
  },
  tabSelect(e) {
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      MainCur: e.currentTarget.dataset.id,
      VerticalNavTop: (e.currentTarget.dataset.id - 1) * 50
    })
  },
  VerticalMain(e) {
    let that = this;
    let list = this.data.list;
    let tabHeight = 0;
    if (this.data.load) {
      for (let i = 0; i < list.length; i++) {
        console.log("list: "+list[i])
        let view = wx.createSelectorQuery().select("#main-" + list[i].id);
        view.fields({
          size: true
        }, data => {
          list[i].top = tabHeight;
          tabHeight = tabHeight + data.height;
          list[i].bottom = tabHeight;
        }).exec();
      }
      that.setData({
        load: false,
        list: list
      })
    }
    let scrollTop = e.detail.scrollTop + 20;
    for (let i = 0; i < list.length; i++) {
      if (scrollTop > list[i].top && scrollTop < list[i].bottom) {
        that.setData({
          VerticalNavTop: (list[i].id - 1) * 50,
          TabCur: list[i].id
        })
        return false
      }
    }
  },
  gotoClassStuList: function (e) {
    var className=e.currentTarget.dataset.classnamedata;
    wx.navigateTo({
      url: '/pages/classStuList/classStuList?className='+className,
    })
  }
})