package com.cal.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cal.pro.model.Employee;
import com.cal.pro.model.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping("/create")
	@ResponseBody
	public String create(String name, String email) {
		Employee user = null;
		try {
			user = new Employee("m@gmail", "mert");
			employeeDao.save(user);
		} catch (Exception e) {
			return "Error creating the user: " + e.toString();
		}
		return "Employee success created! (id = " + user.getId() + ")";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id) {
		try {
			Employee employee = new Employee(id);
			employeeDao.delete(employee);
		} catch (Exception e) {
			return "Error deleting the user: " + e.toString();
		}
		return "Employee succesfully deleted! ";
	}

	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String employeeId = null;
		try {
			Employee employee = employeeDao.findByEmail(email);
			employeeId = String.valueOf(employee.getId());
		} catch (Exception e) {
			return "Employee not found!";
		}
		return "The Employee id is: " + employeeId;
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(Long id, String email, String name) {
		try {
			Employee employee = employeeDao.findById(id).orElse(null); // orElse(null) veya Optional<Employee> -->null
																		// degere karsı
			employee.setName(name);
			employee.setEmail(email);
			employeeDao.save(employee);
		} catch (Exception e) {
			return "Error updating the user: " + e.toString();
		}
		return "Employee succesfully updated!";
	}
}
