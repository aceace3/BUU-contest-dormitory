// pages/stuRegister/stuRegister.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  
  data: {
    
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var timestamp = Date.parse(new Date());
    var date = new Date(timestamp);
    //获取年份  
    var Y = date.getFullYear();
    //获取月份  
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    //获取当日日期 
    var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    this.setData({
      beginDate: Y+'-'+M+'-'+D,
      endDate: Y + '-' + M + '-' + D,
    });
    

  },
  beginDateChange(e) {
    this.setData({
      beginDate: e.detail.value
    })
  },
  endDateChange(e) {
    this.setData({
      endDate: e.detail.value
    })
  },
  textareaInput(e) {
    this.setData({
      textareaValue: e.detail.value
    })
  },
  showModal(e) {
    var beginDate=this.data.beginDate;
    var endDate=this.data.endDate;
    var textareaValue=this.data.textareaValue;

    wx.request({
      url: 'http://localhost:8080/AskFoeLeavePaperController/addInfo',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
          'beginDate': beginDate,
          'endDate': endDate,
          'leaveReasonText':textareaValue
        },
        success:function(res){
           

  
        }
    })
    this.setData({
      modalName: e.currentTarget.dataset.target
    })

  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  }
})