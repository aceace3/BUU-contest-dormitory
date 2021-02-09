//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    motto: 'Hi 欢迎使用系统！',
    userInfo: {
      
    },
    username:'0001',
    password:'0002',
    career:'0003',

    
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  //页面跳转
  gotoIdentitySelect:function(e){
    wx.navigateTo({
      url: '/pages/identitySelect/identitySelect',
    })
   
  },
  //获取input
  username:function(e){
  this.setData({
    username:e.detail.value
  })
},
password:function(e){
  this.setData({
    password:e.detail.value
  })
},
radioChange:function(e){
  console.log("radioChange------------")
  this.setData({
    career:e.detail.value
  })
},

  login:function(e){
    var that=this;
    var username=that.data.username;
    var password=that.data.password;
    var career=that.data.career;

    console.log(username+","+password+","+career)

    wx.request({
      url: 'http://localhost:8080/LoginController/login',
        method:'POST',
        header:{'content-type':'application/json; charset=UTF-8'},
        data:{
          'username': username,
          'password': password
        },
        success:function(res){
            var accountChecked = res.data.success;
            var roleChecked=res.data.dataMap.data.role.id;
            console.log("回调函数:"+accountChecked+"  "+roleChecked+"  "+career)

            if(accountChecked == 1 && career==roleChecked){
              //手动存取sessionid，为了shiro验证
              wx.removeStorageSync('sessionid');
              wx.setStorageSync("sessionid", res.header["Set-Cookie"]);
              console.log(res)
                wx.showToast({
                    title: '登录成功',
                    duration:2000
                })
                if(career=="2"){
                  //stu
                  wx.switchTab({
                    url: '/pages/stuMain/stuMain',
                  })
                }else if(career=="3"){
                  //tea
                  wx.navigateTo({
                    url: '/pages/teacherFunc/teacherFunc',
                  })
                }
            }else{
                wx.showToast({
                    title: '登录失败',
                    duration:2000
                })
            }
        }
    })

  }
})
