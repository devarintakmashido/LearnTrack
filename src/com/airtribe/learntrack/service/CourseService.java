package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public Course addCourse(String name, String description, int durationInWeeks) {
        Course course = new Course(
                IdGenerator.getNextCourseId(),
                name,
                description,
                durationInWeeks
        );
        courseRepository.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + id + " not found");
        }
        return course;
    }

    public void deactivateCourse(int id) {
        Course course = getCourseById(id);
        course.deactivate();
    }
}
