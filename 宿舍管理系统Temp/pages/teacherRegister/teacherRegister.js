// pages/teacherRegister/teacherRegister.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cipher:'',
    teacherName:'',
    phone:'',
    password:'',
    confirmPass:''
  },
  cipher:function(e){
    this.setData({
      cipher:e.detail.value
    })
  },teacherName:function(e){
    this.setData({
      teacherName:e.detail.value
    })
  },phone:function(e){
    this.setData({
      phone:e.detail.value
    })
  },password:function(e){
    this.setData({
      password:e.detail.value
    })
  },confirmPass:function(e){
    this.setData({
      confirmPass:e.detail.value
    })
  },
registerBtn:function(e){
  var that=this;
  var cipher=that.data.cipher
  var teacherName=that.data.teacherName
  var phone=that.data.phone
  var password=that.data.password
  var confirmPass=that.data.confirmPass

  wx.request({
    url: 'http://localhost:8080/RegisterController/teacherRegister',
      method:'POST',
      header:{'content-type':'application/json; charset=UTF-8'},
      data:{
          cipher:cipher,
          teacherName:teacherName,
          phone:phone,
          password:password,
          confirmPass:confirmPass
      },
      success:function(res){
          var resData = res.data;
          console.log("回调函数:"+resData)
  
          if(resData == true){
              wx.showToast({
                  title: '注册成功',
                  duration:2000
              })
          }else{
              wx.showToast({
                  title: '注册失败',
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