/*import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';

class HomeScreen extends StatefulWidget {
 
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('React Básico'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Flutter Básico'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Git Básico'),
            ),
          ),
        ],
      ),
      appBar: AppBar(
        title: Text('Adicionar Roadmap'),
        backgroundColor: Colors.grey,
      ),
    );
  }
}
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* */

import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  // get color => null;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(children: [
        /* Container(
          color: Colors.green,
          width: double.infinity,
          height: 70.0,
          child: Center(
            child: Text('Home'),
          ),
        ),*/

        //top: 10,
        // left: 25,
        // height: 15, //o eixo y
        // width: 150, //o tamanho

        SizedBox(
          width: 0,
          height: 25,
        ),

        Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              /*Container(
                margin: EdgeInsets.all(30.0),
                width: 15,
                height: 15,
                decoration: BoxDecoration(
                  color: Colors.blue,
                  shape: BoxShape.circle,
                ),
              ),*/
              Container(
                //margin: EdgeInsets.all(10.0),
                width: 200,
                height: 50,
                child: Center(
                  child: ElevatedButton(
                    onPressed: () {},
                    child:
                        //Column(children: [
                        Center(
                      child: Text("Minha evolução"),
                    ),
                    // Center(
                    // child: Text("Faltam x pontos para o próximo nível"),
                    //),
                    //]

                    style: ButtonStyle(
                      elevation: MaterialStateProperty.all(5),
                      shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                        RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(500.0),
                          side: BorderSide(color: Colors.red),
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ]),

        //),

        Container(
          //    margin: EdgeInsets.all(5.0),

          width: 200,
          height: 50,
          child: ElevatedButton(
            onPressed: () {},
            child: Column(
              children: [
                Center(child: Text("Level 8")),
                Center(child: Text("Faltam x pontos para o 9")),
              ],
            ),
            style: ButtonStyle(
              elevation: MaterialStateProperty.all(5),
              shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(500.0),
                  side: BorderSide(color: Colors.red),
                ),
              ),
            ),
          ),
        ),

        SizedBox(
          width: 50,
          height: 50,
        ),

        SizedBox(
          //margin: EdgeInsets.all(42.0),
          width: 500,
          height: 300,
          child: Column(children: [
            Card(
              child: Text("Meus últimos roadmaps"),
            ),
            SizedBox(
              width: 25,
              height: 25,
            ),
            Row(children: [
              SizedBox(
                width: 200,
                height: 100,
                child: Column(
                  children: [
                    //child: Center(
                    Center(child: Text("React Básico")),
                    Center(child: Text("------------------------")),
                    Center(child: Text("Completo 34%")),
                    Center(child: Text("Última vez: ontem")),
                  ],
                ),
              ),
              SizedBox(
                width: 200,
                height: 100,
                child: Column(
                  children: [
                    //child: Center(
                    Center(child: Text("Educação Financeira")),
                    Center(child: Text("-----------------------")),
                    Center(child: Text("Completo 71%")),
                    Center(child: Text("Última vez: ontem")),
                  ],
                ),
              ),
            ]),
            Text("Ver tudo"),
          ]),
        ),

        SizedBox(
          width: 500,
          height: 100,
          child: Column(
            children: [
              Center(
                child: Text("Minhas conquistas"),
              ),
              SizedBox(
                width: 0,
                height: 30,
              ),
              Row(
                children: [
                  SizedBox(
                    width: 30,
                    height: 0,
                  ),
                  Center(
                    child: Text("#1"),
                  ),
                  SizedBox(
                    width: 150,
                    height: 0,
                  ),
                  Center(
                    child: Text("#2"),
                  ),
                  SizedBox(
                    width: 150,
                    height: 0,
                  ),
                  Center(
                    child: Text("#3"),
                  ),
                ],
              )
            ],
          ),
        )
      ]),
      appBar: AppBar(
        title: Center(
          child: Text('Home'),
        ),
        backgroundColor: Colors.grey,
      ),
    );
  }
}
                
              
        
  
     /* color: Colors.blueGrey,
  
      children: [

          SizedBox(
  
    width: 250,
  
    height: 200,
  
    child: RaisedButton(
  
      color: Colors.blue,
  
      child: Text('Woolha', style: TextStyle(color: Colors.white)),
  
      onPressed: () {},
  
    ),),

      SizedBox(
  
    width: 250,
  
    height: 200,
  
    child: RaisedButton(
  
      color: Colors.blue,
  
      child: Text('Woolha', style: TextStyle(color: Colors.white)),
  
      onPressed: () {},
  
    ),),t
      ]

      //child: Text('Woolha', style: TextStyle(color: Colors.white)),
  
      onPressed: () {},
  
    ),


    ),
        
      ]),
    );
  }
}
*/
      
    /*     appBar: AppBar(
        title: Text('home'),
        backgroundColor: Colors.grey,
      ),

* 
* Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Minha evolução'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Level 10: Faltam x pontos para o próximo nivel'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Quero trocar meus pontos por mimos e presentes'),
            ),
          ),

          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Meus últimos roadmaps'),
            ),
          ),

          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('React Básico'),
            ),
          ),

          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Educação Financeira'),
            ),
          ),

          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Minhas últimas conquistas'),
            ),
          ),






        ],*/
        /*child:
    *           Text(
          'Green',
          style: TextStyle(
              color: Colors.white,
              fontSize: 20
          ),
        ),*/

/* child: Stack(
          children: [
            Container(
              height: double.infinity,
              width: double.infinity,
              color: Colors.white,
            ),
            Container(
              width: double.infinity,
              child: SingleChildScrollView(
                padding: const EdgeInsets.symmetric(
                  horizontal: 40.0,
                  vertical: 80.0,
                ),
                child: Column(
                  children: <Widget>[
                    Text(
                      "Pet Store",
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 23,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(
                      height: 40,
                    ),
                    InputTextField(
                      controller: _emailController,
                      keyboardType: TextInputType.emailAddress,
                      hintText: 'Digite seu email',
                      prefixIcon: Icon(Icons.email),
                      labelText: 'Email',
                      visiblePassword: true,
                      hasSufixIcon: false,
                    ),
                    InputTextField(
                      controller: _passwordController,
                      keyboardType: TextInputType.text,
                      hintText: 'Digite sua senha',
                      prefixIcon: Icon(Icons.lock),
                      labelText: 'Senha',
                      visiblePassword: false,
                      hasSufixIcon: true,
                    ),
                    Container(
                      alignment: Alignment.centerRight,
                      child: InkWell(
                        onTap: () {},
                        child: Text(
                          "Esqueceu sua senha?",
                          style: TextStyle(
                            color: Colors.red,
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Container(
                      alignment: Alignment.centerLeft,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          Checkbox(
                            value: _rememberMe,
                            onChanged: _handleCheckbox,
                          ),
                          Text('Remember me'),
                        ]
                      ),
                    ),
                    Container(
                      width: double.infinity,
                      child: ElevatedButton(
                        onPressed: () {},
                        child: Text("Login"),
                        style: ButtonStyle(
                          elevation: MaterialStateProperty.all(5),
                          shape:
                              MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30.0),
                              side: BorderSide(color: Colors.red),
                            ),
                          ),
                        ),
                      ),
                    ),
                    Column(
                      children: [
                        SizedBox(
                          height: 10,
                        ),
                        Text('-- OR --'),
                        SizedBox(
                          height: 10,
                        ),
                        Text(
                          'Sign In With',
                          style: TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                    Padding(
                      padding: EdgeInsets.all(15),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          GestureDetector(
                            onTap: () {},
                            child: Container(
                              height: 60,
                              width: 60,
                              decoration: BoxDecoration(
                                shape: BoxShape.circle,
                                color: Colors.white,
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.black,
                                    offset: Offset(0, 2),
                                    blurRadius: 6,
                                  ),
                                ],
                                image: DecorationImage(
                                  image: AssetImage('assets/images/google.png'),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    GestureDetector(
                      onTap: () => Navigator.pushNamed(context, '/register'),
                      child: RichText(
                        text: TextSpan(
                          children: [
                            TextSpan(
                              text: 'Ainda nao tem uma conta? ',
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: 18,
                                fontWeight: FontWeight.w400,
                              ),
                            ),
                            TextSpan(
                              text: 'Registre-se',
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: 18,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
*/
/*  }
}
*/













/*appBar: AppBar()(		
		//backgroundColor: Colors.blueGrey,
		title: Text('Home'),
		backgroundColor: Colors.blueGrey[900],
	),
);}}*/
	
	/*Center(
					child: Image(
						image:
							 NetworkImage('https://negociodecozinha.com.br/wp-content/uploads/2017/07/tapioca2.jpg'),
					),
				),
			),
	Container(
        	height: 80,
		Stack(
		child:
			Container(
				height: 200,
           			decoration: BoxDecoration(color: Colors.green),
			),
		),	
	),
);
}
}


*/
     /*@override
    Size size = MediaQuery.of(context).size;
    return Builder(
      builder: (context) {
        return Column(
          chil  dren: <Widget>[
            

            
                
                
                    ),
                    ),

                    
                  ),

                  child: TextField(
                      decoration: InputDecoration(
                        hintText: "Minha evolução",
                        hintStyle: TextStyle(
                          color: Colors.grey.withOpacity(0.5)
                        ),
                        enabledBorder: InputBorder.none,
                        focusedBorder: InputBorder.none,

                      ),
                    ),
                    ),

              child: Container( 
                      alignment: Alignment.center,
                      margin: EdgeInsets.all(30),
                      //symmetric(horizontal: kDefaultPadding),
                      height: 200,
                      decoration: BoxDecoration(
                        color: Colors.grey,
                        borderRadius: BorderRadius.circular(40),
                        boxShadow: [
                          BoxShadow(
                            offset: Offset  (0, 10),
                            blurRadius: 50,
                            color: Colors.white.withOpacity(0.25)
                          )
                        ]
                      ),
                    child: TextField(
                      decoration: InputDecoration(
                        hintText: "Level 10: Falta x pontos pro próximo level <br> Pontos: 9999 <br> Quero trocar meus pontos por mimos e presentinhos",
                        hintStyle: TextStyle(
                          color: Colors.white.withOpacity(0.5)
                        ),
                        enabledBorder: InputBorder.none,
                        focusedBorder: InputBorder.none,

                      ),
                    ),
              
                ],
              ),
            )
          ],
        );
      }
    );
  }

    //  buildAppBar(){
      //  return AppBar(
        //  elevation: 0,
          //leading: IconButton(
         // icon: SvgPicture.asset("assets/icons/menu.svg"),
         // onPressed: () {},
         // ),
        //)
      //}
}*/
