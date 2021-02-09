// pages/askForLeaveRecord/askForLeaveRecord.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // teacherName:'',
    // replyText:'',
    // isHandle:'',
    // Time:'',
    dataList:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (res) {
    var that = this
    wx.request({
        url: 'http://localhost:8080/AskForLeaveController/getStuRecord',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{},
        success:function(res){ 
          console.log("res.data:--- "+res.data)
          for(var i=0;i<res.data.length;i++){
            if(res.data[i][2]==0){res.data[i][2]="等待"}
            if(res.data[i][2]==1){res.data[i][2]="同意"}
            if(res.data[i][2]==2){res.data[i][2]="拒绝"}
          }
            // var teacherName=res.data[0][0]
            // var replyText=res.data[0][1]
            // var isHandle=res.data[0][2]
            // var Time=res.data[0][3]
            var dataList=res.data

            // if(isHandle==0){isHandle="等待"}
            // else if(isHandle==1){isHandle="同意"}
            // else if(isHandle==2){isHandle="拒绝"}

            that.setData({
              // teacherName:teacherName,
              // replyText:replyText,
              // isHandle:isHandle,
              // Time:Time,
              dataList:dataList
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