// pages/teacherMe/teacherMe.js
Page({
  data:{
    teacherName:'',
    userRole:'',
    displayOne:'block',
    displayTwo:'none',
    boardContent:'暂无公告',
    newBoardContent:''
  },
  textareaInput:function(e){
    var newBoardContent=e.detail.value
    this.setData({
      newBoardContent:newBoardContent
    })
  },
  btnOk:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/TeacherMePageController/updateTextInfo',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
          textInfo:this.data.newBoardContent
        },
        success:function(res){
          that.setData({
            displayOne:"block",
            displayTwo:"none"
          })
          that.onLoad()
        }
    })
  },
  btnUpdate:function(){
    this.setData({
      displayOne:"none",
      displayTwo:"block"
    })
    console.log(this.data.displayOne+" "+this.data.displayTwo)
    this.onLoad()
  },
  btnCancel:function(){
    this.setData({
      displayOne:"block",
      displayTwo:"none"
    })
    this.onLoad()

  },
  gotoTeacherFunc: function (e) {
    wx.navigateTo({
      url: '/pages/teacherFunc/teacherFunc',
    })
  },
  onLoad:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/TeacherMePageController/teacherInfo',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8','cookie':wx.getStorageSync("sessionid")},
        data:{
         
        },
        success:function(res){
            var teacherName=res.data.teacherName
            var userRole=res.data.userRole
            var boardContent=res.data.boardContent
            that.setData({
              teacherName:teacherName,
              userRole:userRole,
              boardContent:boardContent
            })
  
  
           
        }
    })
  }
})