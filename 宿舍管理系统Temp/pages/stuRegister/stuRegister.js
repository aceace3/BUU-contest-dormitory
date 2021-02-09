// pages/stuRegister/stuRegister.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stuName:'',
    stuGender:'',
    stuID:'',
    stuPhone:'',
    stuClass:'',
    stuDormitory:'',
    stuPassword:'',
    stuConfirmPass:''

  },
stuName:function(e){
  this.setData({
    stuName:e.detail.value
  })
},
stuGender:function(e){
  this.setData({
    stuGender:e.detail.value
  })
},
stuID:function(e){
  this.setData({
    stuID:e.detail.value
  })
},
stuPhone:function(e){
  this.setData({
    stuPhone:e.detail.value
  })
},
stuClass:function(e){
  this.setData({
    stuClass:e.detail.value
  })
},
stuDormitory:function(e){
  this.setData({
    stuDormitory:e.detail.value
  })
},
stuPassword:function(e){
  this.setData({
    stuPassword:e.detail.value
  })
},
stuConfirmPass:function(e){
  this.setData({
    stuConfirmPass:e.detail.value
  })
},
registerBtn:function(e){
  var that=this;
  var stuName=that.data.stuName
  var stuGender=that.data.stuGender
  var stuID=that.data.stuID
  var stuPhone=that.data.stuPhone
  var stuClass=that.data.stuClass
  var stuDormitory=that.data.stuDormitory
  var stuPassword=that.data.stuPassword
  var stuConfirmPass=that.data.stuConfirmPass

  var building=stuDormitory.substring(0,3)
  var strTemp=stuDormitory.replace(building,"").split("-")
  var domNumber=strTemp[0]
  var bedNumber=strTemp[1]

  if(stuGender=='男'){
    stuGender=1
  }else if(stuGender=='女'){
    stuGender=0
  }else{
    wx.showToast({
        title: '请输入正确信息！',
        duration:2000
    })
    return false;
}

  console.log(stuName+" * "+stuGender+" * "+stuID+" * "+stuPhone+" * "+stuClass+" * "+stuPassword+" * "+stuConfirmPass+" * "+building+" * "+domNumber+" * "+bedNumber)


  wx.request({
    url: 'http://localhost:8080/RegisterController/stuRegister',
      method:'POST',
      header:{'content-type':'application/json; charset=UTF-8'},
      data:{
          stuName:stuName,
          gender:stuGender,
          studentCode:stuID,
          phone:stuPhone,
          classInfoName:stuClass,
          building:building,
          domNumber:domNumber,
          bedNumber:bedNumber,
          password:stuPassword,
          confirmPass:stuConfirmPass
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