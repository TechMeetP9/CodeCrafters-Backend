-- ============================================
-- DATA.SQL - Code Crafters Test Data
-- ============================================
-- IMPORTANT: Passwords are hashed with BCrypt
-- Password for all test users: "Password123!"
-- ============================================

-- Clean existing data (H2 compatible)
DELETE FROM attendance;
DELETE FROM events;
DELETE FROM users;

-- ============================================
-- INSERT USERS
-- ============================================
-- Password: "Password123!" (BCrypt hash)
INSERT INTO users (id, name, username, email, password, profile_image_url, created_at, updated_at) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Maria Garcia', 'maria.g', 'maria.garcia@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440002', 'Carlos Rodriguez', 'carlos.r', 'carlos.rodriguez@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440003', 'Laura Martinez', 'laura.m', 'laura.martinez@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440004', 'Javier Lopez', 'javier.l', 'javier.lopez@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440005', 'Ana Torres', 'ana.t', 'ana.torres@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440006', 'Pedro Sanchez', 'pedro.s', 'pedro.sanchez@codecrafters.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'https://i.pravatar.cc/300?img=33', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- INSERT CATEGORIES
-- ============================================
INSERT INTO categories (id, name) VALUES
('850e8400-e29b-41d4-a716-446655440001', 'Frontend'),
('850e8400-e29b-41d4-a716-446655440002', 'Backend'),
('850e8400-e29b-41d4-a716-446655440003', 'DevOps'),
('850e8400-e29b-41d4-a716-446655440004', 'Hackathon'),
('850e8400-e29b-41d4-a716-446655440005', 'Data Science'),
('850e8400-e29b-41d4-a716-446655440006', 'Security'),
('850e8400-e29b-41d4-a716-446655440007', 'Testing'),
('850e8400-e29b-41d4-a716-446655440008', 'Design'),
('850e8400-e29b-41d4-a716-446655440009', 'Database'),
('850e8400-e29b-41d4-a716-446655440010', 'Full Stack'),
('850e8400-e29b-41d4-a716-446655440011', 'Blockchain'),
('850e8400-e29b-41d4-a716-446655440012', 'Mobile');

-- ============================================
-- INSERT LOCATIONS
-- ============================================
INSERT INTO locations (id, name) VALUES
('950e8400-e29b-41d4-a716-446655440001', 'Online - Google Meet'),
('950e8400-e29b-41d4-a716-446655440002', 'Barcelona Tech Hub'),
('950e8400-e29b-41d4-a716-446655440003', 'Madrid Innovation'),
('950e8400-e29b-41d4-a716-446655440004', 'Online - Zoom'),
('950e8400-e29b-41d4-a716-446655440005', 'Valencia Hub'),
('950e8400-e29b-41d4-a716-446655440006', 'Online - Teams'),
('950e8400-e29b-41d4-a716-446655440007', 'Sevilla Tech'),
('950e8400-e29b-41d4-a716-446655440008', 'Bilbao Creative'),
('950e8400-e29b-41d4-a716-446655440009', 'Online - Discord'),
('950e8400-e29b-41d4-a716-446655440010', 'Zaragoza Digital'),
('950e8400-e29b-41d4-a716-446655440011', 'Malaga Tech Park');

-- ============================================
-- INSERT EVENTS
-- ============================================

-- Events by Maria Garcia (User 1)
INSERT INTO events (
  id, title, description, event_date, event_time, duration, capacity,
  current_attendees, image_url, category_id, location_id, user_id,
  created_at, updated_at
) VALUES
('650e8400-e29b-41d4-a716-446655440001', 'Introduction to React 19',
 'Learn the new features of React 19 including Server Components, Actions, and rendering improvements. Hands-on workshop with real-world examples.',
 '2025-11-15', '18:00:00', '2 hours', 50, 5,
 'https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800',
 '850e8400-e29b-41d4-a716-446655440001', '950e8400-e29b-41d4-a716-446655440001',
 '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440002', 'Advanced TypeScript',
 'Deep dive into advanced types, generics, decorators, and design patterns with TypeScript 5.3. For experienced developers.',
 '2025-11-20', '19:30:00', '3 hours', 30, 3,
 'https://images.unsplash.com/photo-1587620962725-abab7fe55159?w=800',
 '850e8400-e29b-41d4-a716-446655440001', '950e8400-e29b-41d4-a716-446655440002',
 '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by Carlos Rodriguez (User 2)
('650e8400-e29b-41d4-a716-446655440003', 'Spring Boot Masterclass',
 'Masterclass on Spring Boot 3.2, Spring Security 6, JWT, and best practices for REST APIs. Includes complete project.',
 '2025-11-12', '17:00:00', '4 hours', 40, 4,
 'https://images.unsplash.com/photo-1542831371-29b0f74f9713?w=800',
 '850e8400-e29b-41d4-a716-446655440002', '950e8400-e29b-41d4-a716-446655440003',
 '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440004', 'Microservices with Docker',
 'Learn to deploy and manage microservices using Docker containers and Kubernetes orchestration. Practical case studies.',
 '2025-11-18', '16:00:00', '5 hours', 100, 0,
 'https://images.unsplash.com/photo-1605745341112-85968b19335b?w=800',
 '850e8400-e29b-41d4-a716-446655440003', '950e8400-e29b-41d4-a716-446655440004',
 '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by Laura Martinez (User 3)
('650e8400-e29b-41d4-a716-446655440005', 'AI Hackathon 2025',
 '48-hour intensive hackathon to develop innovative solutions with Artificial Intelligence. Prizes worth €10,000.',
 '2025-11-22', '09:00:00', '48 hours', 80, 5,
 'https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=800',
 '850e8400-e29b-41d4-a716-446655440004', '950e8400-e29b-41d4-a716-446655440005',
 '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440006', 'Machine Learning Basics',
 'Introduction to Machine Learning with Python, scikit-learn, and basic neural network concepts. No prior experience required.',
 '2025-11-14', '18:30:00', '2.5 hours', 60, 0,
 'https://images.unsplash.com/photo-1555949963-aa79dcee981c?w=800',
 '850e8400-e29b-41d4-a716-446655440005', '950e8400-e29b-41d4-a716-446655440006',
 '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by Javier Lopez (User 4)
('650e8400-e29b-41d4-a716-446655440007', 'Cybersecurity Workshop',
 'Intensive workshop on web application security, OWASP Top 10, pentesting, and security best practices.',
 '2025-11-16', '10:00:00', '6 hours', 25, 4,
 'https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800',
 '850e8400-e29b-41d4-a716-446655440006', '950e8400-e29b-41d4-a716-446655440007',
 '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440008', 'Testing with Jest',
 'Hands-on testing workshop in React: unit tests, integration tests, e2e with Cypress, and TDD best practices.',
 '2025-11-19', '19:00:00', '3 hours', 70, 0,
 'https://images.unsplash.com/photo-1516116216624-53e697fedbea?w=800',
 '850e8400-e29b-41d4-a716-446655440007', '950e8400-e29b-41d4-a716-446655440001',
 '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by Ana Torres (User 5)
('650e8400-e29b-41d4-a716-446655440009', 'UX/UI for Developers',
 'Learn design principles, Figma, Design Systems, and how to create attractive and functional interfaces.',
 '2025-11-13', '17:30:00', '3 hours', 35, 0,
 'https://images.unsplash.com/photo-1561070791-2526d30994b5?w=800',
 '850e8400-e29b-41d4-a716-446655440008', '950e8400-e29b-41d4-a716-446655440008',
 '550e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440010', 'Advanced Git and GitHub',
 'Master Git: rebasing, cherry-picking, conflict resolution, Git Flow, GitHub Actions, and CI/CD. For all levels.',
 '2025-11-17', '18:00:00', '2 hours', 150, 5,
 'https://images.unsplash.com/photo-1618401471353-b98afee0b2eb?w=800',
 '850e8400-e29b-41d4-a716-446655440003', '950e8400-e29b-41d4-a716-446655440009',
 '550e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Events by Pedro Sanchez (User 6)
('650e8400-e29b-41d4-a716-446655440011', 'PostgreSQL Tuning',
 'PostgreSQL database optimization: indexes, queries, explain analyze, partitioning, and best practices.',
 '2025-11-21', '16:30:00', '4 hours', 80, 0,
 'https://images.unsplash.com/photo-1544383835-bda2bc66a55d?w=800',
 '850e8400-e29b-41d4-a716-446655440009', '950e8400-e29b-41d4-a716-446655440001',
 '550e8400-e29b-41d4-a716-446655440006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440012', 'Next.js 14 Full Stack',
 'Full stack development with Next.js 14: App Router, Server Actions, RSC, authentication, and Vercel deployment.',
 '2025-11-25', '19:00:00', '3.5 hours', 45, 0,
 'https://images.unsplash.com/photo-1627398242454-45a1465c2479?w=800',
 '850e8400-e29b-41d4-a716-446655440010', '950e8400-e29b-41d4-a716-446655440010',
 '550e8400-e29b-41d4-a716-446655440006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- More varied events
('650e8400-e29b-41d4-a716-446655440013', 'Blockchain and Web3',
 'Introduction to blockchain development: Solidity, Smart Contracts, DApps, and the Web3 ecosystem.',
 '2025-11-23', '18:00:00', '4 hours', 90, 0,
 'https://images.unsplash.com/photo-1639762681485-074b7f938ba0?w=800',
 '850e8400-e29b-41d4-a716-446655440011', '950e8400-e29b-41d4-a716-446655440004',
 '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440014', 'Flutter for React Devs',
 'Transition from React to Flutter: similarities, differences, widgets, state management, and cross-platform development.',
 '2025-11-24', '17:00:00', '3 hours', 50, 0,
 'https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800',
 '850e8400-e29b-41d4-a716-446655440012', '950e8400-e29b-41d4-a716-446655440011',
 '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('650e8400-e29b-41d4-a716-446655440015', 'GraphQL vs REST APIs',
 'Practical comparison between GraphQL and REST: when to use each, Apollo, queries, mutations, and subscriptions.',
 '2025-11-26', '19:30:00', '2 hours', 120, 0,
 'https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=800',
 '850e8400-e29b-41d4-a716-446655440002', '950e8400-e29b-41d4-a716-446655440001',
 '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- INSERT ATTENDANCE
-- ============================================

-- Event 1: Introduction to React 19 (5 attendees)
INSERT INTO attendance (id, user_id, event_id, joined_at, created_at, updated_at) VALUES
('750e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Event 2: Advanced TypeScript (3 attendees)
('750e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Event 3: Spring Boot Masterclass (4 attendees)
('750e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440012', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Event 5: AI Hackathon 2025 (5 attendees)
('750e8400-e29b-41d4-a716-446655440013', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440014', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440015', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440016', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440017', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Event 7: Cybersecurity (4 attendees)
('750e8400-e29b-41d4-a716-446655440018', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440019', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440020', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440005', '650e8400-e29b-41d4-a716-446655440007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Event 10: Advanced Git and GitHub (5 attendees)
('750e8400-e29b-41d4-a716-446655440022', '550e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440023', '550e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440024', '550e8400-e29b-41d4-a716-446655440003', '650e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440025', '550e8400-e29b-41d4-a716-446655440004', '650e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440026', '550e8400-e29b-41d4-a716-446655440006', '650e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
