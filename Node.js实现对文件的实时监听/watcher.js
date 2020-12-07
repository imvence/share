
const fs 		 = require('fs')
const readline 	 = require('readline')
const request	 = require('request')

const filePath 	 = '/Users/mac/Desktop/watch/'				//需要监听的位置
const fileListen = 'log.txt'								//需要监听的文件名
const api		 = 'https://test.api.com/?a=connector'		//通信地址，需要对应修改
const passable   = [	//需要跳过的列表
					'Hello',
					'你好'
				   ]	

let lineNow 	 = 0	//初始行指针位置
let readContent  = ''	//定义可擦除全局内容载体


//初始化文件监听		
initWatch();

//文件动态监听
function initWatch(){
	
	//首先获取被监听文件的初始内容行数
	fs.readFile(filePath+fileListen, function(ree, data){
		let arr = data.toString().split('\n')
		lineNow = arr.length-1
	})

	fs.watch(filePath,(event, filename)=>{
		
		//只对监听文件做处理
		if (filename && filename==fileListen){
			
			//文件读取流
			const rl =  readline.createInterface({
							input: fs.createReadStream(filePath+fileListen,{
								enconding:'utf8'
							}),
							output: null
						});

			let nowRead = 0	//当前读取的内容行数
			
			rl.on('line', function(line) {
				if(line){

					//只对新增的内容进行处理
					if (line && nowRead>=lineNow) {
						readContent = line.toString()
						
						let flg = true
						
						//判断新增的内容是否有包含无需处理的字符
						for(var i in passable){
							if(readContent.indexOf(passable[i])!=-1){
								flg = false
								break
							}
						}

						if(readContent=='') flg = false
						
						if(flg){
							console.log('文件'+fileListen+'发生变化', readContent);
							
							request.post({url:api, form:{content:readContent}},function(error, response, body){
								//这里做监听的案例演示，就不对结果进行处理了
							})
						}
						
						lineNow++
						
					}
					nowRead++
				}
				
			});
			
		}
	})

}