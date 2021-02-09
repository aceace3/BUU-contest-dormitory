// pages/stuFunc/stuFunc.js
Page({

  gotoAskForLeave:function(e){
    wx.navigateTo({
      url: '/pages/askForLeaveFunc/askForLeaveFunc',
    })
  },
  gotoAFLRecord: function (e) {
    wx.navigateTo({
      url: '/pages/askForLeaveRecord/askForLeaveRecord',
    })
  },
  gotoCalendar: function (e) {
    wx.navigateTo({
      url: '/pages/calendar/calendar',
    })
  },
})