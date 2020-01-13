package com.surhoo.sh.common;


public class Api {

    //   public static final String  URL= "https://yaochengkun-shanghu.f.wmeimob.com/api/";
    public static final String URL = "https://xcxtest.shanghusm.com/api/";
//    public static final String URL = "http://zjgweb.imdo.co:34451/api/";
//    public static final String URL = "http://192.168.11.110:8081/api/";

    //生产
//    public static final String URL = "https://xcx.shanghusm.com/api/";


    //首页
    //首页数据
    public static final String HOMEPAGE = URL + "index";
    //分类搜索
    public static final String SEARCHCATEGORY = URL + "index/search";
    //全部搜索
    public static final String SEARCHALL = URL + "index/search/all";


    //获取验证码
    public static final String GETVERIFYCODE = URL + "login/code";
    //手机号登录
    public static final String login = URL + "login/sign";

    //场景
    //场景分页列表
    public static final String SCENARIOCATEGORYLIST = URL + "scene/detail";
    //查看场景详情以及下级场景
    public static final String SCENARIODETAIL = URL + "scene";


    //商品
    //查看分类商品列表
    public static final String GOODSLIST = URL + "goods/classify";
    //查看商品详情
    public static final String GOODSDETAIL = URL + "goods/detail";
    //查看所有的一级分类
    public static final String GOODSLEVERONECATEGORY = URL + "goodsClassify/classify";
    //查看所有的2级分类
    public static final String GOODSLEVERTWOCATEGORY = URL + "goodsClassify/second";
    //查看商品的所有评价
    public static final String ALLGOODSCOMMENTS = URL + "goods/evaluate";


    //购物车
    //添加到购物车
    public static final String ADDTOCART = URL + "goods/car";
    //购物车数量
    public static final String SHOPPINGCARTNUMBER = URL + "goods/car/num";
    //修改购物车数量
    public static final String CHANGESHOPPINGCARTNUMBER = URL + "goods/car";
    //删除购物车数量
    public static final String DELETESHOPPINGCARTNUMBER = URL + "goods/car/del";


    //设计师
    //设计师的主页
    public static final String DESIGNERINFO = URL + "designer/info";
    //设计师的主页素材
    public static final String MATERIALOFDESIGNER = URL + "designer/material";
    //设计师的动态
    public static final String DYNAMICOFDESIGNER = URL + "designer/trends";
    //设计师的主页成品
    public static final String FINISHPRODUCTOFDESIGNER = URL + "designer/works";
    //设计师的标签
    public static final String designerLabel = URL + "designer/label";

    //店铺
    //查看店铺详情
    public static final String SHOPDETAIL = URL + "shop";
    //查看店铺下的商品
    public static final String SHOPGOODS = URL + "shop/goods";

    //购物车
//    查询购物车
    public static final String SHOPPINGCART = URL + "goods/car";

    //地址
    //查看收货地址列表
    public static final String ADDRESSLIST = URL + "user/address/getList";
    //删除收货地址
    public static final String ADDRESSDELETE = URL + "user/address/delete";
    //查询订单运费
    public static final String GETORDERPOSTAGE = URL + "goods/pay/getRreight";


    //添加地址
    public static final String ADDADDRESS = URL + "user/address/add";
    //修改地址
    public static final String updateAddress = URL + "user/address/update";
    //删除地址
    public static final String deleteAddress = URL + "user/address/delete";


    //发票
    //发票列表
    public static final String INVOICELIST = URL + "user/invoice/getList";
    //保存发票信息
    public static final String SAVEINVOICEINFO = URL + "user/invoice/add";
    //删除发票信息
    public static final String DELETEINVOICE = URL + "user/invoice/delete";


    //素材
    //素材详情
    public static final String MATERIALDETAIL = URL + "goods/material";
    public static final String materialLabel = URL + "index/label";


    //订单
    //下单
    public static final String payOrder = URL + "goods/pay";
    //查看订单列表
    public static final String getOrderList = URL + "order/getOrderList";
    //删除订单
    public static final String deleteOrder = URL + "order/delOrder";
    //取消订单
    public static final String cancelOrder = URL + "order/cancelOrder";
    //添加订单评价
    public static final String addOrderEvaluate = URL + "order/evaluate";
    //获取下单信息
    public static final String getPayInfo = URL + "goods/getPayInfo";
    //获取订单详情
    public static final String getOrderDetail = URL + "order";
    //订单号付款
    public static final String payUseOrderNo = URL + "goods/pay/orderNo";
    //确认收货
    public static final String confirmOrder = URL + "order/confirmOrder";
    //查看物流
    public static final String checkExpress = URL + "order/express";

    //收藏
    //查看用户收藏1 商品 2 素材 3设计师 4店铺

    public static final String collect = URL + "collect";
    public static final String getCollectList = URL + "collect/";


    //用户
    //查询用户信息
    public static final String userInfo = URL + "user/info";
    //查询用户信息
    public static final String updateuserInfo = URL + "user/update";

}
