<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="recursos/css/Treant.css">
    <link rel="stylesheet" type="text/css" href="recursos/css/basic-example.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 
 
</head>
<body>
<style>
 
</style>
 
<div class="jumbotron text-center">
  <h1>Práctica 2</h1>
  <p>Yrving David Conde Cubas</p> 
</div>
  
<div class="container"> 
     <div class="chart" id="basic-example"></div>
    <script src="recursos/js/raphael.js"></script>
    <script src="recursos/js/Treant.js"></script>
   
    <script src="recursos/js/basic-example.js"></script>
    <script>
        var data = ${res}; 
         var chart_config=[];
        $(document).ready(function () {
            var config = {
                container: "#basic-example",

                connectors: {
                    type: 'step'
                },
                node: {
                    HTMLclass: 'PracticaID3'
                }
            }; 
            chart_config.push(config);
            generateOutput(data,'a',-1);
            new Treant( chart_config );
        });
       
        
       function generateOutput(node,idx,idxPadre){   
            if(node.attribute !== undefined) {
                var nameParent = "ce"+idx; 
                if(idxPadre !== -1){
                    chart_config.push(nameParent = {
                         parent: idxPadre,
                        text: {
                            name: node.attribute.header 
                        }
                    });
                }
                else{
                    chart_config.push(nameParent = {

                        text: {
                            name: node.attribute.header 
                        }
                    });
                }
                
                for (var i=0;i<node.attribute.subset.length;++i) {
                    var nameChild = "ce"+idx+i; 
                    var value = (node.attribute.subset[i]);
                    chart_config.push(nameChild = {
                        parent: nameParent,
                        text: {
                            name: value
                        }
                    }); 
                    if(node.result === undefined) { 
                         generateOutput(node.child[value],idx+1,nameChild)
                            //output+=this.child.get(generateOutput(this.attribute.getSubset().get(i)));
                    } 
                }	
            }
            if(node.result !== undefined) {
                if(node.result){
                    var nameChildSI = nameChild+"Si";
                    chart_config.push(nameChildSI = {
                        parent: idxPadre,
                        text: {
                            name: "+" 
                        }
                    }); 
                }
                else{
                    var nameChildNo = nameChild+"No";
                    chart_config.push(nameChildNo = {
                        parent: idxPadre,
                        text: {
                            name: "-" 
                        }
                    });  
                }  	
            }  
        } 
        
      
        
        /*
            
    ceo = {
        text: {
            name: "Mark Hill",
            title: "Chief executive officer",
            contact: "Tel: 01 213 123 134",
        },
        image: "../headshots/2.jpg"
    },

    cto = {
        parent: ceo,
        text:{
            name: "Joe Linux",
            title: "Chief Technology Officer",
        },
        stackChildren: true,
        image: "../headshots/1.jpg"
    },*/
    
   
    
   
    </script>
</div> 
</body>
</html>