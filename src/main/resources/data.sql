-- ============================================
-- DATA.SQL - Code Crafters Test Data
-- ============================================
-- IMPORTANT: Passwords are hashed with BCrypt
-- Password for all test users: "Password123!"
-- ============================================

-- Clean existing data (if any)
TRUNCATE TABLE attendance CASCADE;
TRUNCATE TABLE events CASCADE;
TRUNCATE TABLE users CASCADE;

-- ============================================
-- INSERT USERS
-- ============================================
-- Password: "Password123!" (BCrypt hash)
INSERT INTO users (id, name, email, password, profile_image_url, created_at) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Maria Garcia', 'maria.garcia@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=1', NOW() - INTERVAL '30 days'),
('550e8400-e29b-41d4-a716-446655440002', 'Carlos Rodriguez', 'carlos.rodriguez@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=12', NOW() - INTERVAL '25 days'),
('550e8400-e29b-41d4-a716-446655440003', 'Laura Martinez', 'laura.martinez@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=5', NOW() - INTERVAL '20 days'),
('550e8400-e29b-41d4-a716-446655440004', 'Javier Lopez', 'javier.lopez@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=15', NOW() - INTERVAL '15 days'),
('550e8400-e29b-41d4-a716-446655440005', 'Ana Torres', 'ana.torres@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=9', NOW() - INTERVAL '10 days'),
('550e8400-e29b-41d4-a716-446655440006', 'Pedro Sanchez', 'pedro.sanchez@codecrafters.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=33', NOW() - INTERVAL '5 days');

-- ============================================
-- INSERT EVENTS
-- ============================================

-- Events by Maria Garcia (User 1)
INSERT INTO events (id, title, description, event_date, event_time, duration, location, event_type, category, capacity, current_attendees, image_url, user_id, created_at) VALUES
('650e8400-e29b-41d4-a716-446655440001', 'Introduction to React 19', 'Learn the new features of React 19 including Server Components, Actions, and rendering improvements. Hands-on workshop with real-world examples.', '2025-11-15', '18:00:00', '2 hours', 'https://meet.google.com/abc-defg-hij', 'online', 'Frontend', 50, 12, 'https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800', '550e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '5 days'),
('650e8400-e29b-41d4-a716-446655440002', 'Advanced TypeScript', 'Deep dive into advanced types, generics, decorators, and design patterns with TypeScript 5.3. For experienced developers.', '2025-11-20', '19:30:00', '3 hours', 'Barcelona Tech Hub, Carrer del Rec 27', 'presencial', 'Frontend', 30, 8, 'https://images.unsplash.com/photo-1587620962725-abab7fe55159?w=800', '550e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '4 days'),

-- Events by Carlos Rodriguez (User 2)
('650e8400-e29b-41d4-a716-446655440003', 'Spring Boot Masterclass', 'Masterclass on Spring Boot 3.2, Spring Security 6, JWT, and best practices for REST APIs. Includes complete project.', '2025-11-12', '17:00:00', '4 hours', 'Madrid Innovation Center, Calle Gran Via 28', 'presencial', 'Backend', 40, 25, 'https://images.unsplash.com/photo-1542831371-29b0f74f9713?w=800', '550e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '8 days'),
('650e8400-e29b-41d4-a716-446655440004', 'Microservices with Docker and Kubernetes', 'Learn to deploy and manage microservices using Docker containers and Kubernetes orchestration. Practical case studies.', '2025-11-18', '16:00:00', '5 hours', 'https://zoom.us/j/123456789', 'online', 'DevOps', 100, 45, 'https://images.unsplash.com/photo-1605745341112-85968b19335b?w=800', '550e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '7 days'),

-- Events by Laura Martinez (User 3)
('650e8400-e29b-41d4-a716-446655440005', 'AI Hackathon 2025', '48-hour intensive hackathon to develop innovative solutions with Artificial Intelligence. Prizes worth €10,000.', '2025-11-22', '09:00:00', '48 hours', 'Valencia Innovation Hub, Av. de las Cortes 10', 'presencial', 'Hackathon', 80, 62, 'https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=800', '550e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '12 days'),
('650e8400-e29b-41d4-a716-446655440006', 'Machine Learning for Beginners', 'Introduction to Machine Learning with Python, scikit-learn, and basic neural network concepts. No prior experience required.', '2025-11-14', '18:30:00', '2.5 hours', 'https://teams.microsoft.com/meet/abc123', 'online', 'Data Science', 60, 18, 'https://images.unsplash.com/photo-1555949963-aa79dcee981c?w=800', '550e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '6 days'),

-- Events by Javier Lopez (User 4)
('650e8400-e29b-41d4-a716-446655440007', 'Cybersecurity and Ethical Hacking', 'Intensive workshop on web application security, OWASP Top 10, pentesting, and security best practices.', '2025-11-16', '10:00:00', '6 hours', 'Sevilla Tech Center, Calle Sierpes 45', 'presencial', 'Security', 25, 20, 'https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800', '550e8400-e29b-41d4-a716-446655440004', NOW() - INTERVAL '10 days'),
('650e8400-e29b-41d4-a716-446655440008', 'Testing with Jest and Testing Library', 'Hands-on testing workshop in React: unit tests, integration tests, e2e with Cypress, and TDD best practices.', '2025-11-19', '19:00:00', '3 hours', 'https://meet.google.com/xyz-abcd-efg', 'online', 'Testing', 70, 32, 'https://images.unsplash.com/photo-1516116216624-53e697fedbea?w=800', '550e8400-e29b-41d4-a716-446655440004', NOW() - INTERVAL '3 days'),

-- Events by Ana Torres (User 5)
('650e8400-e29b-41d4-a716-446655440009', 'UX/UI Design for Developers', 'Learn design principles, Figma, Design Systems, and how to create attractive and functional interfaces. For developers with no design experience.', '2025-11-13', '17:30:00', '3 hours', 'Bilbao Creative Space, Gran Via 56', 'presencial', 'Design', 35, 28, 'https://images.unsplash.com/photo-1561070791-2526d30994b5?w=800', '550e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '9 days'),
('650e8400-e29b-41d4-a716-446655440010', 'Advanced Git and GitHub', 'Master Git: rebasing, cherry-picking, conflict resolution, Git Flow, GitHub Actions, and CI/CD. For all levels.', '2025-11-17', '18:00:00', '2 hours', 'https://discord.gg/codecrafters', 'online', 'DevOps', 150, 89, 'https://images.unsplash.com/photo-1618401471353-b98afee0b2eb?w=800', '550e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '2 days'),

-- Events by Pedro Sanchez (User 6)
('650e8400-e29b-41d4-a716-446655440011', 'PostgreSQL Performance Tuning', 'PostgreSQL database optimization: indexes, queries, explain analyze, partitioning, and best practices.', '2025-11-21', '16:30:00', '4 hours', 'https://meet.google.com/pqr-stuv-wxy', 'online', 'Database', 80, 41, 'https://images.unsplash.com/photo-1544383835-bda2bc66a55d?w=800', '550e8400-e29b-41d4-a716-446655440006', NOW() - INTERVAL '1 day'),
('650e8400-e29b-41d4-a716-446655440012', 'Next.js 14 Full Stack', 'Full stack development with Next.js 14: App Router, Server Actions, RSC, authentication, and Vercel deployment.', '2025-11-25', '19:00:00', '3.5 hours', 'Zaragoza Digital Hub, Paseo Independencia 23', 'presencial', 'Full Stack', 45, 15, 'https://images.unsplash.com/photo-1627398242454-45a1465c2479?w=800', '550e8400-e29b-41d4-a716-446655440006', NOW()),

-- More varied events
('650e8400-e29b-41d4-a716-446655440013', 'Blockchain and Web3 Fundamentals', 'Introduction to blockchain development: Solidity, Smart Contracts, DApps, and the Web3 ecosystem.', '2025-11-23', '18:00:00', '4 hours', 'https://zoom.us/j/987654321', 'online', 'Blockchain', 90, 52, 'https://images.unsplash.com/photo-1639762681485-074b7f938ba0?w=800', '550e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '11 days'),
('650e8400-e29b-41d4-a716-446655440014', 'Flutter for React Developers', 'Transition from React to Flutter: similarities, differences, widgets, state management, and cross-platform development.', '2025-11-24', '17:00:00', '3 hours', 'Malaga Tech Park, Av. Tecnologia 1', 'presencial', 'Mobile', 50, 33, 'https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800', '550e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '13 days'),
('650e8400-e29b-41d4-a716-446655440015', 'GraphQL vs REST APIs', 'Practical comparison between GraphQL and REST: when to use each, Apollo, queries, mutations, and subscriptions.', '2025-11-26', '19:30:00', '2 hours', 'https://meet.google.com/gql-rest-api', 'online', 'Backend', 120, 67, 'https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=800', '550e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '14 days');

-- ============================================
-- INSERT ATTENDANCE
-- ============================================

-- Event 1: Introduction to React 19 (12 attendees)
INSERT INTO attendance (id, user_id, event_id, joined_at) VALUES
('750e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '4 days'),
('750e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '4 days'),
('750e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '3 days'),
('750e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '3 days'),
('750e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '2 days'),

-- Event 2: Advanced TypeScript (8 attendees)
('750e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '3 days'),
('750e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '2 days'),
('750e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '1 day'),

-- Event 3: Spring Boot Masterclass (25 attendees)
('750e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '7 days'),
('750e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '6 days'),
('750e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '5 days'),
('750e8400-e29b-41d4-a716-446655440012', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '5 days'),

-- Event 5: AI Hackathon 2025 (many attendees - all users registered)
('750e8400-e29b-41d4-a716-446655440013', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '11 days'),
('750e8400-e29b-41d4-a716-446655440014', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '10 days'),
('750e8400-e29b-41d4-a716-446655440015', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '9 days'),
('750e8400-e29b-41d4-a716-446655440016', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '8 days'),
('750e8400-e29b-41d4-a716-446655440017', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440005', NOW() - INTERVAL '7 days'),

-- Event 7: Cybersecurity (20 attendees - almost full)
('750e8400-e29b-41d4-a716-446655440018', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440007', NOW() - INTERVAL '9 days'),
('750e8400-e29b-41d4-a716-446655440019', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440007', NOW() - INTERVAL '8 days'),
('750e8400-e29b-41d4-a716-446655440020', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440007', NOW() - INTERVAL '7 days'),
('750e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440007', NOW() - INTERVAL '6 days'),

-- Event 10: Advanced Git and GitHub (popular online event)
('750e8400-e29b-41d4-a716-446655440022', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440010', NOW() - INTERVAL '2 days'),
('750e8400-e29b-41d4-a716-446655440023', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440010', NOW() - INTERVAL '1 day'),
('750e8400-e29b-41d4-a716-446655440024', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440010', NOW() - INTERVAL '1 day'),
('750e8400-e29b-41d4-a716-446655440025', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440010', NOW() - INTERVAL '6 hours'),
('750e8400-e29b-41d4-a716-446655440026', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440010', NOW() - INTERVAL '3 hours');