// pages/classStuList/classStuList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    className:'',
    stuName:'',
    gender:'',
    studentCode:'',
    notDays:'',
    allList:[]

  },
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      className:options.className
    })
   var className=this.data.className
    var that=this;
    wx.request({
      url: 'http://localhost:8080/StuListOfClassController/stuList',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
          className:className
           
        },
        success:function(res){
            var res = res.data;
            console.log(res[0][0])
            
            var allList=new Array();
            for(var i=0;i<res.length;i++){
              var stuList=new Array();
              var stuName=res[i][0]
              var gender=res[i][1]
              var studentCode=res[i][2]
              // var notDays=res[i][3]---------------------------------打卡天数数据有误，需修改：根据学号查询天数
              stuList.push(stuName)
              stuList.push(gender)
              stuList.push(studentCode)

              allList.push(stuList)
            }

            that.setData({
              allList:allList
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