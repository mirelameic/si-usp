import 'package:flutter/material.dart';

class InputTextField extends StatelessWidget {
  final TextInputType keyboardType;
  final Icon? prefixIcon;
  final bool? hasSufixIcon;
  final String? hintText;
  final String labelText;
  final bool? visiblePassword;
  final controller;
  final handlePlainPassword;

  InputTextField({
    required this.keyboardType,
    required this.labelText,
    required this.controller,
    this.hintText,
    this.visiblePassword,
    this.prefixIcon,
    this.hasSufixIcon,
    this.handlePlainPassword,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          alignment: Alignment.centerLeft,
          height: 60,
          child: TextField(
            controller: controller,
            obscureText: visiblePassword != null && (visiblePassword) as bool
                ? false
                : true,
            keyboardType: visiblePassword != null && (visiblePassword) as bool
                ? keyboardType
                : TextInputType.visiblePassword,
            style: TextStyle(color: Colors.black),
            decoration: InputDecoration(
              contentPadding:
                  const EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.00),
              border: OutlineInputBorder(),
              prefixIcon: prefixIcon,
              suffixIcon: hasSufixIcon != null && (hasSufixIcon) as bool
                  ? IconButton(
                      onPressed: handlePlainPassword,
                      icon: Icon(
                        visiblePassword != null && (visiblePassword) as bool
                            ? Icons.visibility
                            : Icons.visibility_off,
                      ),
                    )
                  : null,
              hintText: hintText,
              label: Text(labelText),
            ),
          ),
        ),
      ],
    );
  }
}
