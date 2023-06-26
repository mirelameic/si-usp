import 'package:flutter/material.dart';
import 'package:rp_project/widgets/input-text-field.dart';

class RegisterScreen extends StatefulWidget {
  @override
  _RegisterScreenState createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  final _nameController = TextEditingController();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  final _phoneController = TextEditingController();
  bool hasSufixIcon = false;

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    _nameController.dispose();
    _emailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    _phoneController.dispose();
    super.dispose();
  }

  void _handleCheckPassword() {
    setState(() {
      hasSufixIcon = !hasSufixIcon;
    });
  }

  Future<void> _handleResgister() async {
    // TODO
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: GestureDetector(
        onTap: () => FocusScope.of(context).unfocus(),
        child: Stack(
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
                      controller: _nameController,
                      keyboardType: TextInputType.name,
                      hintText: 'Como gostaria de ser chamado',
                      prefixIcon: Icon(Icons.person),
                      labelText: 'Nome',
                      visiblePassword: true,
                      hasSufixIcon: false,
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
                      visiblePassword: hasSufixIcon,
                      hasSufixIcon: true,
                      handlePlainPassword: _handleCheckPassword,
                    ),
                    InputTextField(
                      controller: _confirmPasswordController,
                      keyboardType: TextInputType.text,
                      hintText: 'Confirmacao de senha',
                      prefixIcon: Icon(Icons.lock),
                      labelText: 'Confirmacao da senha',
                      visiblePassword: hasSufixIcon,
                      hasSufixIcon: true,
                      handlePlainPassword: _handleCheckPassword,
                    ),
                    InputTextField(
                      controller: _phoneController,
                      keyboardType: TextInputType.phone,
                      hintText: '(DDD)12345-6789',
                      prefixIcon: Icon(Icons.phone),
                      labelText: 'Telefone',
                      visiblePassword: true,
                      hasSufixIcon: false,
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Container(
                      width: double.infinity,
                      child: ElevatedButton(
                        onPressed: _handleResgister,
                        child: Text("Registar"),
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
