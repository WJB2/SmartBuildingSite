// 自动添加css兼容属性
const px2rem=require('postcss-plugin-px2rem');
const autoprefixer=require('autoprefixer');
module.exports={
    plugins:[
         autoprefixer({browsers:'ie>8'}),
         px2rem({rootValue:75,propBlackList:['75px']})
    ],
}