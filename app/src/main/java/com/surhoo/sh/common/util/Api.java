package com.surhoo.sh.common.util;


public class Api {


    //   public static final String  URL= "https://yaochengkun-shanghu.f.wmeimob.com/api/";
//    public static final String URL = "https://xcxtest.shanghusm.com/api/";
    public static final String URL = "http://zjgweb.imdo.co:34451/api/";





    //首页
    //首页数据
    public static final String HOMEPAGE = URL+"index";
    //分类搜索
    public static final String SEARCHCATEGORY = URL + "index/search";
    //全部搜索
    public static final String SEARCHALL = URL + "index/search/all";



    //获取验证码
    public static final String GETVERIFYCODE = URL + "login/code";
    //手机号登录
    public static final String LOGIN = URL + "login/signIn";


    //场景

    //场景分页列表
    public static final String SCENARIOCATEGORYLIST = URL + "scene/detail";
    //查看场景详情以及下级场景
    public static final String SCENARIODETAIL = URL + "scene";



    //商品
    //查看分类商品列表
    public static final String GOODSLIST = URL + "goods/classify";
    //查看商品详情
    public static final String GOODSDETAIL = URL + "goods";
    //查看所有的一级分类
    public static final String GOODSLEVERONECATEGORY = URL + "goodsClassify/classify";
    //查看所有的2级分类
    public static final String GOODSLEVERTWOCATEGORY = URL + "goodsClassify/second/14";
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
    public static final String DESIGNERLABEL = URL + "designer/label";


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
    //查看收货地址列表
    public static final String ADDRESSDELETE = URL + "user/address/delete";


    //发票
    //发票列表
    public static final String INVOICELIST = URL + "user/invoice/getList";



    //收藏

    //查看用户收藏1 商品 2 素材 3设计师 4店铺
    public static final String COLLECT = URL + "collect";


    //素材

    //素材详情
    public static final String MATERIALDETAIL = URL + "goods/material";
}
