// pages/addClass/addClass.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    className:''
  },
  className:function(e){
    this.setData({
      className:e.detail.value
    })
  },
  subBtn:function(e){
    var className=this.data.className;
    wx.request({
      url: 'http://localhost:8080/AddClassController/addClass',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
          className:className
        },
        success:function(res){
            var resData = res.data;
            console.log("回调函数:"+resData)
  
            if(resData == true){
                wx.showToast({
                    title: '提交成功',
                    duration:2000
                })
            }else{
                wx.showToast({
                    title: '添加失败',
                    duration:2000
                })
            }
        }
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})