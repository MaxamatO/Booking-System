update hotels h SET number_of_rooms = (SELECT COUNT(r.hotel_id) FROM hotel_room r WHERE r.hotel_id = h.hotel_id);