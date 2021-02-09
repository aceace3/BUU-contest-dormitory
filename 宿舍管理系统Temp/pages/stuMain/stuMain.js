// pages/stuMain/stuMain.js
var util=require('../../utils/util.js');

//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    longitude: 0,
    latitude: 0,
    speed: 0,
    accuracy: 0,
    time:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取当前时间
    var TIME = util.formatTime(new Date());
    this.setData({
      time: TIME,
    });
    console.log(TIME);

    //地图获取位置
    var that = this
    wx.showLoading({
      title: "定位中",
      mask: true
    })
    wx.getLocation({
      type: 'gcj02',
      altitude: true,//高精度定位
      //定位成功，更新定位结果
      success: function (res) {
        console.log(res)
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy


        that.setData({
          longitude: longitude,
          latitude: latitude,
          speed: speed,
          accuracy: accuracy
        })
      },
      //定位失败回调
      fail: function () {
        wx.showToast({
          title: "定位失败",
          icon: "none"
        })
  },
      complete: function () {
        //隐藏定位中信息进度
        wx.hideLoading()
      },   
  })
},
dakaBtn:function(e){
  var longitude=this.data.longitude
  var latitude=this.data.latitude
  var localeDate=String(this.data.time).split(" ")[0]
  console.log(longitude+" "+latitude+" "+localeDate)

  wx.request({
    url: 'http://localhost:8080/MapController/checkClockIn',
      method:'POST',
      header:{'content-type':'application/json; charset=UTF-8'},
      data:{
          longitude:longitude,
          latitude:latitude,
          localeDate:localeDate
      },
      success:function(res){
          var resData = res.data;
          console.log("回调函数:"+resData)

          if(resData == true){
              wx.showToast({
                  title: '打卡成功',
                  duration:2000
              })
          }else{
              wx.showToast({
                  title: '打卡失败',
                  duration:2000
              })
          }
      }
  })
}
})