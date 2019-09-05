package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ChatMessage;

@Controller
public class ChatController {
	private List<ChatMessage> messages;

	ChatController() {
		messages = new ArrayList<>();
	}
	// 메인페이지로
	@RequestMapping("/chat/main")
	public String showMain() {
		return "chat/main";
	}
	//메세지 저장
	@RequestMapping("/chat/addMessage")
	@ResponseBody
	public Map addMessage(String writer, String body) {
		// 3가지
		// 번호
		// 작성자
		// 내용
		long id = messages.size();
		ChatMessage newChatMessage = new ChatMessage(id, writer, body);
		messages.add(newChatMessage);

		Map rs = new HashMap<String, Object>();
		rs.put("msg", "메세지가 입력되었습니다.");
		rs.put("resultCode", "S-1");
		rs.put("addedMessage", newChatMessage);

		return rs;
	}
	//모든 메세지 가져오기
	@RequestMapping("/chat/getAllMessages")
	@ResponseBody
	public List<ChatMessage> getAllMessages() {
		return messages;
	}
	// 새로운 메세지만 가져오기
	@RequestMapping("/chat/getMessages")
	@ResponseBody
	public List<ChatMessage> getAllMessages(int from) {
		return messages.subList(from, messages.size());
	}
	// 메세지 삭제
	@RequestMapping("/chat/clearMessages")
	@ResponseBody
	public Map clearMessages() {
		messages.clear();

		Map rs = new HashMap<String, Object>();
		rs.put("msg", "모든 메세지가 삭제되었습니다.");
		rs.put("resultCode", "S-1");

		return rs;
	}
}