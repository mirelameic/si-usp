import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:rp_project/routes.dart';

class roadmap extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Roadmap',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Roadmap X'),
          backgroundColor: Colors.grey,
      ),
      body: ListaTopicos(),
    ),
    );
  }
}

class ListaTopicos extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children:[
          Card(
            child: ListTile(
              trailing: Icon(
                Icons.check_box,
                ),
              title: Text('Tópico 1'),
            ),
          ),
          Card(
            child: ListTile(
              trailing: Icon(
                Icons.check_box,
                ),
              title: Text('Tópico 2'),
            ),
          ),
          Card(
            child: ListTile(
              trailing: Icon(
                Icons.lock,
                ),
              title: Text('Tópico 3'),
            ),
          ),
          Card(
            child: ListTile(
              trailing: Icon(
                Icons.lock,
                ),
              title: Text('Tópico 4'),
            ),
          ),
          Card(
            child: ListTile(
              trailing: Icon(
                Icons.lock,
                ),
              title: Text('Tópico 5'),
            ),
          ),
        ],
      ),
    );
  }


}
