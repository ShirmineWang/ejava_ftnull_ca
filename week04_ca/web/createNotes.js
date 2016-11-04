/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        $(function(){
            $("#create").on("click", function(){
                $('#title').blur(function() {
                    if( !$(this).val() ) {
                        alert("title can not be null");
                        return false;                        
                    }
                });
            });
        });       

