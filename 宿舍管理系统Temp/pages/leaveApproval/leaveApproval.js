// pages/leaveApproval/leaveApproval.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    approvalList:'',
    replyText:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    wx.request({
      url: 'http://localhost:8080/TeacherListAFLController/listAskForLeave',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
           
        },
        success:function(res){
            var approvalList=res.data
            console.log(approvalList)

            that.setData({
             approvalList:approvalList
            })

  
           
        }
    })
  },
  replyText:function(e){
    console.log(e.detail.value)
    this.setData({
      replyText: e.detail.value,
    })
    },
showModal:function(e){
  var that =this
  var id=e.currentTarget.dataset.id
  var replyText=this.data.replyText
  var isHandle;
  if(e._relatedInfo.anchorTargetText=="同意"){
    isHandle=1
  }else if(e._relatedInfo.anchorTargetText=="拒绝"){
    isHandle=2
  }
  console.log("id:"+id+"  replyText:"+replyText)
  console.log(e._relatedInfo.anchorTargetText)
  wx.request({
    url: 'http://localhost:8080/TeacherListAFLController/updateReplyText',
      method:'POST',
      header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
      data:{
        replyText:replyText,
        id:id,
        isHandle:isHandle
      },
      success:function(res){
          var approvalList=res.data
          console.log(approvalList)
          that.onLoad()

          that.setData({
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