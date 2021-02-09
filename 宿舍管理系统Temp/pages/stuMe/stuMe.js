// pages/stuMe/stuMe.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "studentCode": '',
    "dormNumber": '',
    "className": '',
    "content": '',
    "username": ''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    wx.request({
      url: 'http://localhost:8080/StuMinePageController/getStuMine',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
           
        },
        success:function(res){
            var studentCode = res.data.studentCode;
            var dormNumber = res.data.dormNumber;
            var className = res.data.className;
            var content = res.data.content;
            var username = res.data.username;

            that.setData({
              studentCode:studentCode,
              dormNumber:dormNumber,
              className:className,
              content:content,
              username:username
            })

  
           
        }
    })
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