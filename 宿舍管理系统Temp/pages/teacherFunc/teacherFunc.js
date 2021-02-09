// pages/teacherFunc/teacherFunc.js
Page({
  data:{
    count:''
  },
  onLoad:function(){
    var that=this;
    wx.request({
      url: 'http://localhost:8080/AskForLeaveController/getCount',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
           
        },
        success:function(res){
            var count=res.data

            that.setData({
              count:count
            })

  
           
        }
    })
  },
  gotoTeacherMe:function(e){
    wx.navigateTo({
      url: '/pages/teacherMe/teacherMe',
    })
  },
  gotoMyClassFunc: function (e) {
    wx.navigateTo({
      url: '/pages/myClassFunc/myClassFunc',
    })
  },
  gotoAddClass: function (e) {
    wx.navigateTo({
      url: '/pages/addClass/addClass',
    })
  }
  ,
  
  gotoLeaveApproval: function (e) {
    wx.navigateTo({
      url: '/pages/leaveApproval/leaveApproval',
    })
  }
})